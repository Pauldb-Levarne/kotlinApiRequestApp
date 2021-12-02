package com.example.makingcallsv2

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*



interface ToDoApiService {


    @GET("todos")
    suspend fun getTodos(): String

    @DELETE("todos/{id}")
    suspend fun deleteItem(@Path("id") todoId: Int)

    @POST("todos")
    suspend fun postItem(@Body toDoItem: ToDoItem): ToDoItem
}

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object ToDoApi {
    val retrofitService: ToDoApiService by lazy {
        retrofit.create(ToDoApiService::class.java)
    }
}

