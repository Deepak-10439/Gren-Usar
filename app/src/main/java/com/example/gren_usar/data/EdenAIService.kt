package com.example.gren_usar.data

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface EdenAIService {
    @Multipart
    @POST("object-detection/launch")
    fun launchExecution(
        @Part file: MultipartBody.Part
    ): Call<LaunchExecutionResponse>

    @GET("object-detection/result/{execution_id}")
    fun getExecutionResult(
        @Path("execution_id") executionId: String
    ): Call<DetectionResponse>
}
