package com.urovo.core.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.device.ScanManager
import android.device.scanner.configuration.PropertyID
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.urovo.core.mvp.BasePresenter
import java.lang.ClassCastException

/**
 * create by 大白菜
 * description
 */
open abstract class BaseActivity<P : BasePresenter<*, *>> : AppCompatActivity() {

    //惰性加载
    protected val mPresenter by lazy { createPresenter() }
    private val mScanManager by lazy { ScanManager() }

    companion object {
        const val SCAN_ACTION = ScanManager.ACTION_DECODE
    }

    abstract fun setLayout(): Any
    abstract fun createPresenter(): P
    abstract fun enableScan(): Boolean
    abstract fun onResult(strBarcode: String)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        when (setLayout()) {
            is View -> {
                setContentView(setLayout() as View)
            }
            is Int -> {
                setContentView(setLayout() as Int)
            }
            else -> {
                throw ClassCastException("setLayout() type must be Int or View")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (enableScan())
            registerScanReceiver()
    }

    override fun onPause() {
        super.onPause()
        if (enableScan())
            unregisterReceiver(mScanReceiver)
    }

    private fun registerScanReceiver() {
        val filter = IntentFilter()
        val idBuffer = intArrayOf(PropertyID.WEDGE_INTENT_ACTION_NAME, PropertyID.WEDGE_INTENT_DATA_STRING_TAG)
        val buffer = mScanManager.getParameterString(idBuffer)
        if (buffer != null && buffer[0] != null && buffer[0] != "") {
            filter.addAction(buffer[0])
        } else {
            filter.addAction(SCAN_ACTION)
        }
        registerReceiver(mScanReceiver, filter)
    }

    private val mScanReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            //播放声音
            val byCode = intent.getByteArrayExtra(ScanManager.DECODE_DATA_TAG)
            val barcodeLength = intent.getIntExtra(ScanManager.BARCODE_LENGTH_TAG, 0)
            val strBarcode = byCode?.let { String(it, 0, barcodeLength) }
            strBarcode?.let { onResult(strBarcode) }
        }
    }

}
