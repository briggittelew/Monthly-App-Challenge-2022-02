package com.briggittelew.wikistarswars

import android.database.Observable
import androidx.annotation.StringRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService{
    @GET
    suspend fun getPersonajes(@Url url: String):Response<PeopleResult>
    //suspend fun getPersonajes(): Observable<PeopleResult>
}