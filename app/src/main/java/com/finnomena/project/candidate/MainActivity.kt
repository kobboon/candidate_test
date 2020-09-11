package com.finnomena.project.candidate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.finnomena.project.candidate.fragment.PokemonListFragment
import com.finnomena.project.candidate.util.ManageFragment

class MainActivity : AppCompatActivity() {

    private var doubleBackToExit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ManageFragment.addFragment(
            supportFragmentManager,
            PokemonListFragment(),
            R.id.layout_content
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onBack()
    }

    private fun onBack() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            if (doubleBackToExit) {
                this.finish()
            } else {
                doubleBackToExit = true
                Handler().postDelayed({ doubleBackToExit = false; }, 1000)
            }
        } else {
            if (supportFragmentManager.backStackEntryCount > 1) {
                supportFragmentManager.popBackStack()
            } else {
                super.onBackPressed()
            }
        }
    }

}
