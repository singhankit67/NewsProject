package com.example.mydaiilynews

import android.annotation.SuppressLint
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*

object ApiClient {
    //is used to make the http request to the url using ssl handshake
    var BASE_URL:String="https://newsapi.org/v2/"
    val apiInterface:ApiInterface
    get() = RetrofitClient.getClient(BASE_URL).create(ApiInterface::class.java)
    fun getTopHeadlines():String{
        val apiUrl1 = StringBuilder( "https://newsapi.org/v2/top-headlines?language=en")
            .append("&apiKey=")
            .append(Common.API_KEY)
            .toString()
        return apiUrl1
    }
    fun getAllNews(query:String):String{
        val  apiUrl2 = StringBuilder("https://newsapi.org/v2/everything?q=")
            .append(query)
            .append("&language=en")
            .append("&sortBy=popularity")
            .append("&apiKey=")
            .append(Common.API_KEY)
            .toString()
        return apiUrl2
    }
//    fun getApiClient(): Retrofit? {
//        if(retrofit == null){
////            val gson = GsonBuilder()
////                .setLenient()
////                .create()
//            retrofit = Retrofit.Builder() // t5his calls the url
//                .client(getUnsafeHttpClient().build()) //for calling the http request
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BASE_URL)
//                .build()
//
//        }
//        return retrofit
//    }
    fun getUnsafeHttpClient() : OkHttpClient.Builder {
        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
                object : X509TrustManager {
                    @SuppressLint("TrustAllX509TrustManager")
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {
                    }

                    @SuppressLint("TrustAllX509TrustManager")
                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf<X509Certificate>()
                    }

                    //val acceptedIssuers: Array<X509Certificate?>?
                        //get() = arrayOf()
                }
            )

            // Install the all-trusting trust manager
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.getSocketFactory()
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(object : HostnameVerifier {
                @SuppressLint("BadHostnameVerifier")
                override fun verify(hostname: String?, session: SSLSession?): Boolean {
                    return true
                }
            })
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
//fun getApiUrl(source:String, apiKey:String):String {
//    val apiUrl = StringBuilder("https://newsapi.org/v2/top-headlines?country=de&category=")
//    return apiUrl.append(source)
//        .append("&apiKey=")
//        .append(apiKey)
//        .toString()
//   // to make http request acc to source
//}
}