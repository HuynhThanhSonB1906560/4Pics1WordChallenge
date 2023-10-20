package vn.tutorials.a4pics1wordchallenge.utils

import android.content.Context
import android.content.SharedPreferences


class MySharedPreference private constructor(context: Context) {
    var level: Int
        get() = sharedPreferences.getInt("level", 0)
        set(level) {
            editor.putInt("level", level).apply()
        }
    var coinCount: Int
        get() = sharedPreferences.getInt("coin", 100)
        set(count) {
            editor.putInt("coin", count).apply()
        }
    var namePlayer: String?
        get() = sharedPreferences.getString("namePlayer", null)
        set(name) {
            editor.putString("namePlayer", name).apply()
        }
    var gameMode: String?
        get() = sharedPreferences.getString("gameMode", null)
        set(mode) {
            editor.putString("gameMode", mode).apply()
        }
    var itemClicked: Boolean
        get() = sharedPreferences.getBoolean("click", false)
        set(click) {
            editor.putBoolean("click", click).apply()
        }

    companion object {
        var mySharedPreference: MySharedPreference? = null
        lateinit var sharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
        fun getInstance(context: Context): MySharedPreference? {
            if (mySharedPreference == null) {
                mySharedPreference = MySharedPreference(context)
            }
            return mySharedPreference
        }
    }
    init {
        sharedPreferences = context.getSharedPreferences("4 Pics 1 Word Challenge", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }
}
