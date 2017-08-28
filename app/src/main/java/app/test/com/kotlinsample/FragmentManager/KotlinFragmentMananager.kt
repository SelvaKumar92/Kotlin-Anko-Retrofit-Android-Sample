package app.test.com.kotlinsample.FragmentManager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import app.test.com.kotlinsample.R


class KotlinFragmentMananager(val myContext: AppCompatActivity) {

    fun updateFragment(aFragement: Fragment, cleanStack: Boolean = false) {
        val ft = myContext.supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.replace(R.id.activity_base_content, aFragement)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun clearBackStack() {
        val manager = myContext.supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}
