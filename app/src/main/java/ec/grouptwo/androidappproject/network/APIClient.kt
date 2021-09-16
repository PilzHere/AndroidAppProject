package ec.grouptwo.androidappproject.network

import android.util.Log
import ec.grouptwo.androidappproject.game.Game
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    var test: String = "testa"


    val retrofit = Retrofit.Builder()
        .baseUrl(RetrofitInterface.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiInterface(): RetrofitInterface {
        val api = retrofit.create(RetrofitInterface::class.java)
        return api
    }

    fun SearchForGameUsingTitle(title: String): List<Game>? {
        val api: RetrofitInterface = APIClient().getApiInterface()
        val call = api.getSpelByTitle(title)
        var gameList: List<Game>? = null

        call?.enqueue(object : Callback<List<Game?>?> {
            override fun onResponse(
                call: Call<List<Game?>?>,
                response: Response<List<Game?>?>
            ) {
                gameList = response.body() as List<Game>?
                gameList?.forEach{
                    println(it.external)
                }
                test = "bajsa"
                println(test)
            }

            override fun onFailure(call: Call<List<Game?>?>, t: Throwable) {
                Log.d("SearchForGameUsingTitle", "Did not work. Reason: " + t)
            }
        })
        println(test)
        println("LIST INSIDE METHOD APCLIENT!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + gameList)
        return gameList
    }
}