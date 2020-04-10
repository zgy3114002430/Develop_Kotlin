package com.urovo.develop.service

import retrofit2.http.POST
import java.util.*
import com.urovo.develop.response.BaseResponse
import io.reactivex.Observable

/**
 * create by 大白菜
 * description
 */
interface RequestApi {

    @POST
    fun SignIn(): Observable<BaseResponse<String>>
}