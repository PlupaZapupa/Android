package ru.me.a26preferencesframework

import android.os.Build
import android.os.Bundle
import android.preference.PreferenceFragment

class MyPrefsFragment : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            addPreferencesFromResource(R.xml.settings);
        }
        else{
            fragmentManager.beginTransaction()
                .replace(android.R.id.content, MyPrefsFragment())
                .commit()
        }
    }
}