package br.com.mmiiranda.a4chords.data.remote

import br.com.mmiiranda.a4chords.data.remote.dto.ArtistResponse
import br.com.mmiiranda.a4chords.data.remote.dto.CifraResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CifraApi {
    @GET("artist/{artist}")
    suspend fun getArtist(
        @Path("artist") artist: String
    ): ArtistResponse

    @GET("cifra")
    suspend fun getCifra(
        @Query("url") url: String
    ): CifraResponse
}
