package app.test.com.kotlinsample.AnkoDesignActivity

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import app.test.com.kotlinsample.R
import app.test.com.kotlinsample.R.id.center
import org.jetbrains.anko.*
import java.util.*

/**
 * Created by selvakumark on 24-08-2017.
 */
public class UIActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UIAction(this@UIActivity).setContentView(this@UIActivity)
        /* setContentView(R.layout.activity_main)
         loadDrawer()*/
        if (isNetworkConnected()) {
            Toast.makeText(this@UIActivity, "Network Connected", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@UIActivity, "Network Disconnected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager// 1
        val networkInfo = connectivityManager.activeNetworkInfo // 2
        return networkInfo != null && networkInfo.isConnected // 3
    }

    public class UIAction(val uiActivity: UIActivity) : AnkoComponent<UIActivity> {


        override fun createView(ui: AnkoContext<UIActivity>) = with(ui) {

            verticalLayout {
                relativeLayout {
                    textView("Activity code without xml file using Anko Design + Network Connectivity Manager") {
                        textSize = 22f
                        gravity = center
                        setTextColor(R.color.colorPrimaryDark)
                    }.lparams {
                        matchParent
                        weightSum
                        centerHorizontally()
                        topMargin = 5
                        padding = 10
                    }
                }
                listView {
                    var listFrindsUser = ArrayList<String>()
                    listFrindsUser.add("Null-safety")
                    listFrindsUser.add("Smart casts")
                    listFrindsUser.add("String templates")
                    listFrindsUser.add("Properties")
                    listFrindsUser.add("Primary constructors")
                    adapter = ArrayAdapter<String>(uiActivity, R.layout.simple_list, listFrindsUser)
                }.lparams {
                    topMargin = 15
                }
            }
        }
    }

}