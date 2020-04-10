package com.urovo.develop.response

import java.io.Serializable

/**
 * create by 大白菜
 * description
 */
data class BaseResponse<T>(var code: Int, var message: String, var t: T) : Serializable {

}