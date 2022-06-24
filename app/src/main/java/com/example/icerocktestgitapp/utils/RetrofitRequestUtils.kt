package com.example.icerocktestgitapp.utils

import com.example.icerocktestgitapp.data.resources.Resource
import kotlinx.serialization.SerializationException
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

object RetrofitRequestUtils{
    suspend fun <T>tryRequest(request: suspend () -> Response<T>): Resource<T> {
        return try{
            val response = request.invoke()
            if (response.isSuccessful){
                return response.body()?.let {
                    Resource.Success(it)
                } ?: Resource.Error("Empty data from server")
            }
            else when (response.code()){
                Constants.UNAUTHORIZED_CODE -> Resource.Error("Wrong token", code = Constants.UNAUTHORIZED_CODE)
                else -> Resource.Error("Service Unavailable")
            }
        }catch (e: SerializationException){
            Resource.Error("Data from server unreadable")
        }catch (e: UnknownHostException){
            Resource.Error("No internet connection")
        }catch (e: CancellationException){
            Resource.Error("Cancelled")
        }catch (e: Exception){
            Resource.Error("Unknown error")
        }
    }

    fun okHttpRequest(client: OkHttpClient, url: String): Resource<String>{
        val request = Request.Builder()
            .url(url)
            .build()
        val call = client.newCall(request)
        try{
            val response = call.execute()
            if (response.isSuccessful){
                response.body()?.let{
                    return Resource.Success(it.string())
                }
                return Resource.Error("Empty readme")
            }
            return Resource.Error("No README file", code = response.code())
        }catch (e: UnknownHostException){
            return Resource.Error("No internet connection to get README")
        }catch (e:IOException){
            return Resource.Error("No README file")
        }catch (e:OutOfMemoryError){
            return Resource.Error("No README file")
        }
    }
}