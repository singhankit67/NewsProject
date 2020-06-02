package com.example.mydaiilynews

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //is used to make the http request to the url using ssl handshake
    //var BASE_URL:String="https://newsapi.org"
    var retrofit:Retrofit? = null
    public fun getClient(baseUrl:String?): Retrofit {
        if(retrofit == null){
//            val gson = GsonBuilder()
//                .setLenient()
//                .create()
            retrofit = Retrofit.Builder() // t5his calls the url
                .baseUrl(baseUrl!!)
                //.client(getUnsafeHttpClient()!!.build()) //for calling the http request
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
        return retrofit!!
    }
//    fun getUnsafeHttpClient() : OkHttpClient.Builder? {
//        return try {
//            // Create a trust manager that does not validate certificate chains
//            val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
//                object : X509TrustManager {
//                    @Throws(CertificateException::class)
//                    override fun checkClientTrusted(
//                        chain: Array<X509Certificate?>?,
//                        authType: String?
//                    ) {
//                    }
//
//                    @Throws(CertificateException::class)
//                    override fun checkServerTrusted(
//                        chain: Array<X509Certificate?>?,
//                        authType: String?
//                    ) {
//                    }
//
//                    override fun getAcceptedIssuers(): Array<X509Certificate> {
//
//                        return arrayOf<X509Certificate>()
//                    }
//
//                    //val acceptedIssuers: Array<X509Certificate?>?
//                        //get() = arrayOf()
//                }
//            )
//
//            // Install the all-trusting trust manager
//            val sslContext: SSLContext = SSLContext.getInstance("SSL")
//            sslContext.init(null, trustAllCerts, SecureRandom())
//
//            // Create an ssl socket factory with our all-trusting manager
//            val sslSocketFactory: SSLSocketFactory = sslContext.getSocketFactory()
//            val builder = OkHttpClient.Builder()
//            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
//            builder.hostnameVerifier(object : HostnameVerifier {
//                override fun verify(hostname: String?, session: SSLSession?): Boolean {
//                    return true
//                }
//            })
//            builder
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//    }
//fun getApiUrl(source:String, apiKey:String):String {
//    val apiUrl = StringBuilder("https://newsapi.org/v2/top-headlines?country=de&category=")
//    return apiUrl.append(source)
//        .append("&apiKey=")
//        .append(apiKey)
//        .toString()
   // to make http request acc to source
//}
}