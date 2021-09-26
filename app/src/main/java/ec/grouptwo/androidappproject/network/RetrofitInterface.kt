package ec.grouptwo.androidappproject.network

import ec.grouptwo.androidappproject.game.Game
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("games")
    fun getGameByTitle(@Query("title") title: String): Call<List<Game?>?>?

    companion object {
        const val BASE_URL = "https://www.cheapshark.com/api/1.0/"
    }
}