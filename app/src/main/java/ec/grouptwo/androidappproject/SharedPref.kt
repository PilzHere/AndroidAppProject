package ec.grouptwo.androidappproject

import android.content.Context
import android.content.SharedPreferences

class SharedPref (context: Context) {
    private var sharedPreferences: SharedPreferences = context.getSharedPreferences("File", Context.MODE_PRIVATE)


//Den här metoden sparar Lightmode state FALSE/TRUE

    fun setLightModeState(state:Boolean?){
        val editor = sharedPreferences.edit()
        editor.putBoolean("Lightmode", state!!)
        editor.apply()


    }

    fun getLightModeState():Boolean?{

        val state : Boolean = sharedPreferences.getBoolean("Lightmode", false)
        return state


    }

}
