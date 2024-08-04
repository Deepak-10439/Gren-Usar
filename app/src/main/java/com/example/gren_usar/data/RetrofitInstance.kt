package com.example.gren_usar.data

//object RetrofitInstance {
//    private val logging = HttpLoggingInterceptor().apply {
//        setLevel(HttpLoggingInterceptor.Level.BODY)
//    }
//
//    private val client = OkHttpClient.Builder()
//        .addInterceptor(logging)
//        .build()
//
//    private val retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl("https://app.edenai.run/v2/workflows/033ae9f1-15bf-4118-b71f-6b6c9daaa13f/api-integration") // Replace with your base URL
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    val api: EdenAIService by lazy {
//        retrofit.create(EdenAIService::class.java)
//    }
//}