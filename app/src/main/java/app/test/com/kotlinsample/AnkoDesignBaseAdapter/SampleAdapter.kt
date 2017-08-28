package app.test.com.kotlinsample.AnkoDesignBaseAdapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import app.test.com.kotlinsample.AnkoDatabase.DbHelper
import app.test.com.kotlinsample.R
import org.jetbrains.anko.*
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.select
import java.util.*

/**
 * Created by selvakumark on 24-08-2017.
 */
class SampleAdapter(val activity: Activity) : BaseAdapter() {


    val myDatabase: DbHelper get() = DbHelper.getInstance(activity)
    var list: ArrayList<String> = loadBooks();

    override fun getView(i: Int, v: View?, parent: ViewGroup?): View {
        val item = getItem(i)
        return with(parent!!.context) {
            relativeLayout {
                textView(list.get(i)) {
                    textSize = 22f
                }
                textView("Kotlin") {
                    textSize = 16f
                    textColor = R.color.colorPrimaryDark
                }.lparams {
                    alignParentBottom()
                    alignParentRight()
                }
            }
        }
    }

    override fun getItem(position: Int): String {
        return list.get(position)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return getItem(position) as Long;
    }

    fun loadBooks(): ArrayList<String> {
        var listFrindsUser = ArrayList<String>()
        myDatabase.use {
            select("Customer").parseList(object : MapRowParser<ArrayList<String>> {
                override fun parseRow(columns: Map<String, Any?>): ArrayList<String> {
                    val name = columns.getValue("name")
                    val des = columns.getValue("des")
                    listFrindsUser.add(name.toString() + "\n" + des)
                    return listFrindsUser
                }
            })
        }
        return listFrindsUser
    }

    fun rebuild() {

        list = loadBooks();
        notifyDataSetChanged()
    }
}