package app.test.com.kotlinsample.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.test.com.kotlinsample.CommonValues.CommonConstants
import app.test.com.kotlinsample.CommonValues.DataBaseValues
import app.test.com.kotlinsample.R

/**
 * Created by Aravindh on 27-03-2017.
 */

class Profile : Fragment(), CommonConstants, DataBaseValues {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val aView = inflater!!.inflate(R.layout.fragment_profile, container, false)
        return aView
    }

    override fun onResume() {
        super.onResume()
    }


}
