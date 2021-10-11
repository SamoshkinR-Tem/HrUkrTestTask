package com.hr.ukr_tz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hr.ukr_tz.ui.frgmnt_main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}