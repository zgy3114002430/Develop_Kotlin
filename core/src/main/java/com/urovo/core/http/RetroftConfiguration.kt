package com.urovo.core.http

/**
 * create by 大白菜
 * description
 */
class RetroftConfiguration constructor(builder: Builder) {

    var READTIMEOUT = 0L
    var CONNECTTIMEOUT = 0L
    val BASEURL: String
    var DEBUG: Boolean = false
    val HEADPARAMS: Map<String, String>
    val VERIFIEDHOSTNAMES: List<String>

    init {
        builder.apply {
            READTIMEOUT = read_timeout
            CONNECTTIMEOUT = connect_timeout
            BASEURL = base_url
            DEBUG = debug
            HEADPARAMS = head_params
            VERIFIEDHOSTNAMES = verifiedHostNames
        }

    }

    companion object {

        class Builder {
            var read_timeout = 0L
            var connect_timeout = 0L
            lateinit var base_url: String
            lateinit var head_params: Map<String, String>
            lateinit var verifiedHostNames: List<String>
            var debug = true

            fun readTimeout(timeout: Long): Builder {
                this.read_timeout = timeout
                return this
            }

            fun connectTimeout(timeout: Long): Builder {
                this.connect_timeout = timeout
                return this
            }

            fun baseUrl(url: String): Builder {
                this.base_url = url
                return this
            }

            fun debug(debug: Boolean): Builder {
                this.debug = debug
                return this
            }

            fun addHeaders(params: Map<String, String>) : Builder{
                this.head_params = params
                return this
            }

            fun vertifiedHostNames(list: List<String>): Builder{
                this.verifiedHostNames = list
                return this
            }

            fun build(): RetroftConfiguration{
                return RetroftConfiguration(this)
            }
        }
    }
}