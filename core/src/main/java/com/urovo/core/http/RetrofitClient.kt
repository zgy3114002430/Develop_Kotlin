package com.urovo.core.http

import com.urovo.core.config.ConfigKey
import com.urovo.core.config.Normal
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import retrofit2.Retrofit

/**
 * create by 大白菜
 * description
 */
class RetrofitClient {

    companion object {

        private class OKHttpHolder {

            companion object {
                private val TIME_OUT = Normal.getConfiguration<Long>(ConfigKey.TIME_OUT)
                private val BUILDER = OkHttpClient.Builder()
                private val INTERCEPTORS =
                    Normal.getConfiguration<ArrayList<Interceptor>>(ConfigKey.INTERCEPTOR)

                fun addInterceptor(): OkHttpClient.Builder {
                    INTERCEPTORS?.let {
                        for (interceptor in INTERCEPTORS) {
                            BUILDER.addInterceptor(interceptor)
                        }
                    }
                    return BUILDER
                }
                val OK_HTTP_CLIENT: OkHttpClient = addInterceptor()
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .build()
            }
        }

        private class RetrofitHolder {

            companion object {
                private val BASE_URL = Normal.getConfiguration<String>(ConfigKey.API_HOST)
                val RETROFIT_CLIENT: Retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(OKHttpHolder.OK_HTTP_CLIENT)
                    .build()

            }
        }

        fun getRetrofit(): Retrofit{
            return RetrofitHolder.RETROFIT_CLIENT
        }

    }
}