package easy.dating.foryou.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import easy.dating.foryou._core.BaseActivity
import easy.dating.foryou.activities.MainScreenActivity
import com.github.arturogutierrez.Badges
import com.github.arturogutierrez.BadgesNotSupportedException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import easy.dating.foryou.*
import kotlinx.android.synthetic.main.activity_web_view.*
import me.leolin.shortcutbadger.ShortcutBadger
import java.util.*


/**
 * Created by Andriy Deputat email(andriy.deputat@gmail.com) on 3/13/19.
 */
class SplashActivity : BaseActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    private lateinit var dataSnapshot: DataSnapshot

    private lateinit var database: DatabaseReference
    val REFERRER_DATA = "REFERRER_DATA"
    val badgeCount = 1

    override fun getContentView(): Int = R.layout.activity_web_view


    override fun initUI() {
        webView = web_view
        progressBar = progress_bar
    }


    fun getPreferer(context: Context): String? {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        if (!sp.contains(REFERRER_DATA)) {
            return "Didn't got any referrer follow instructions"
        }
        return sp.getString(REFERRER_DATA, null)
    }


    override fun setUI() {
        logEvent("splash-screen")
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }

            @SuppressLint("deprecated")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.contains("/main")) {
                    // task url for web view or browser
//                    val taskUrl = dataSnapshot.child(TASK_URL).value as String
                    val value = dataSnapshot.child(SHOW_IN).value as String

                    var taskUrl = dataSnapshot.child(TASK_URL).value.toString()

                    when (Locale.getDefault().country.toString()) {
                        "RU" -> taskUrl = dataSnapshot.child("ru").value.toString()
                        "US" -> taskUrl = dataSnapshot.child("us").value.toString()
                        "SE" -> taskUrl = dataSnapshot.child("se").value.toString()
                        "NO" -> taskUrl = dataSnapshot.child("no").value.toString()
                        "IT" -> taskUrl = dataSnapshot.child("it").value.toString()
                        "FR" -> taskUrl = dataSnapshot.child("fr").value.toString()
                        "FI" -> taskUrl = dataSnapshot.child("fi").value.toString()
                        "DK" -> taskUrl = dataSnapshot.child("dk").value.toString()
                        "AT" -> taskUrl = dataSnapshot.child("at").value.toString()
                    }

                    if (value == WEB_VIEW) {
                            startActivity(
                                    Intent(this@SplashActivity, WebViewActivity::class.java)
                                .putExtra(EXTRA_TASK_URL, taskUrl)
                            )
                        finish()
                    } else if (value == BROWSER) {
                        // launch browser with task url
                        val browserIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("")
                        )

                        logEvent("task-url-browser")
                        startActivity(browserIntent)
                        finish()
                    }
                } else if (url.contains("/main")) {
                    startActivity(Intent(this@SplashActivity, MainScreenActivity::class.java))
                    finish()
                }
                progressBar.visibility = View.GONE
                return false
            }
        }

        progressBar.visibility = View.VISIBLE

        val success = ShortcutBadger.applyCount(this, badgeCount)
        if (!success) {
            startService(
                    Intent(this, BadgeIntentService::class.java).putExtra("badgeCount", badgeCount)
            )
        }

        try {
            Badges.setBadge(this, badgeCount)
        } catch (badgesNotSupportedException: BadgesNotSupportedException) {
            Log.d("SplashActivityBadge", badgesNotSupportedException.message)
        }

        val config = YandexMetricaConfig.newConfigBuilder("6e43103a-29f0-42b8-8264-3178b799e6b4").build()
        YandexMetrica.activate(this, config)
        YandexMetrica.enableActivityAutoTracking(this.application)

        database = FirebaseDatabase.getInstance().reference

        Log.d("testest", getPreferer(this))

        Toast.makeText(this, Locale.getDefault().country.toString(), Toast.LENGTH_SHORT).show()

        getValuesFromDatabase({
            dataSnapshot = it


            // load needed url to determine if user is suitable
            webView.loadUrl(it.child(SPLASH_URL).value as String)
        }, {
            Log.d("SplashErrActivity", "didn't work fetchremote")
            progressBar.visibility = View.GONE
        })
    }
}