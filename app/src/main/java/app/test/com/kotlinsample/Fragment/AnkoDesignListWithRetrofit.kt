package app.test.com.kotlinsample.Fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import app.test.com.kotlinsample.AnkoDesignBaseAdapter.SampleAdapter
import app.test.com.kotlinsample.CommonValues.CommonConstants
import app.test.com.kotlinsample.CommonValues.DataBaseValues
import app.test.com.kotlinsample.AnkoDatabase.DbHelper
import app.test.com.kotlinsample.KotlinMainActivity
import app.test.com.kotlinsample.Pojo.Book
import app.test.com.kotlinsample.R
import app.test.com.kotlinsample.RetrofitWebservice.WebserviceAPI
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response
import java.util.*


/**
 * Created by Aravindh on 27-03-2017.
 */

class AnkoDesignListWithRetrofit : Fragment(), CommonConstants, DataBaseValues {

    //List view to show data
    private var myListView: ListView? = null

    val myDatabase: DbHelper get() = DbHelper.getInstance(activity)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val aView = inflater!!.inflate(R.layout.fragment_list, container, false)
        classAndWidgetInitialization(aView)
        return aView
    }


    private fun classAndWidgetInitialization(aView: View) {

        //Initializing the listview
        myListView = aView.findViewById(R.id.listViewBooks) as ListView

        //Calling the method that will fetch data
        getBooks()

        //Setting onItemClickListener to listview
        myListView?.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(activity, "Clicks" + position, Toast.LENGTH_SHORT).show()
        }

    }

    private fun getBooks() {
        //While the app fetched data we are displaying a progress dialog
        val loading = ProgressDialog.show(activity, "Fetching Data", "Please wait...", false, false)

        //Creating a rest adapter
        val adapter = RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build()

        //Creating an object of our api interface
        val api = adapter.create<WebserviceAPI>(WebserviceAPI::class.java!!)

        //Defining the method
        api.getBooks(object : Callback<List<Book>> {
            override fun success(list: List<Book>, response: Response) {

                //Storing the data in our list
                storeDataToDatabase(list)
                //Calling a method to show the list
                showListFromDatabase()

                //Dismissing the loading progressbar
                loading.dismiss()
            }

            override fun failure(error: RetrofitError) {
                //you can handle the errors here
                loading.dismiss()
                Toast.makeText(activity, "Server not reachable", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun storeDataToDatabase(list: List<Book>) {
        myDatabase.use {
            delete("Customer")
            for (i in list!!.indices) {
                insert("Customer", "name" to list!![i].name,
                        "des" to list!![i].price)
            }
        }
    }

    //Our method to show list
    private fun showListFromDatabase() {

        //Creating an array adapter for list view
        val adapter = ArrayAdapter<String>(activity, R.layout.simple_list, loadBooks())
        //Setting adapter to listview
        myListView!!.adapter = SampleAdapter(activity as KotlinMainActivity)
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


    companion object {
        //Root URL of our web service
        val ROOT_URL = "https://api.myjson.com/"
    }
}


