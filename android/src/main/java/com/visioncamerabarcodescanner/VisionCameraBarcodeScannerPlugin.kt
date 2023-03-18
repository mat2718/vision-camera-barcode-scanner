package com.visioncamerabarcodescanner

import com.visioncamerabarcodescanner.BarcodeConverter.convertToArray
import com.visioncamerabarcodescanner.BarcodeConverter.convertToMap
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.Point
import android.graphics.Rect
import android.media.Image
import com.facebook.react.bridge.ReadableNativeArray
import com.facebook.react.bridge.ReadableNativeMap
import com.facebook.react.bridge.WritableNativeArray
import com.facebook.react.bridge.WritableNativeMap
import androidx.camera.core.ImageProxy
import com.google.android.gms.tasks.Tasks
import com.mrousavy.camera.frameprocessor.FrameProcessorPlugin
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.common.internal.ImageConvertUtils
import java.util.ArrayList
import java.util.Arrays
import java.util.HashSet
import java.util.List
import java.util.Set
import android.util.Log

class VisionCameraBarcodeScannerPlugin internal constructor() :
    FrameProcessorPlugin("scanBarcodes") {

    @SuppressLint("NewApi")
    override fun callback(frame: ImageProxy, params: Array<Any>): Any? {
        @SuppressLint("UnsafeOptInUsageError") 
        val mediaImage: Image? = frame.getImage()
        if (mediaImage != null) {
            val image: InputImage =
                InputImage.fromMediaImage(mediaImage, frame.getImageInfo().getRotationDegrees())
            try {
                val array = WritableNativeArray()
                val barcodeScanner = BarcodeScanning.getClient()
                val task = barcodeScanner.process(image)
                // val barcodes= Tasks.await(task)
                .addOnSuccessListener { barcodes ->
                    for (barcode in barcodes) {
                        array.pushMap(convertBarcode(barcode))
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("VisionCameraBarcodeScanner", "Error while trying to process barcode")
                    e.printStackTrace()
                }
                return array
            } catch (e: Exception) {
                Log.e("VisionCameraBarcodeScanner", "Error while trying to process barcode")
                e.printStackTrace()
            }
        }
        return null
    }

    

    private fun convertContent(barcode: Barcode): WritableNativeMap {
        val map = WritableNativeMap()
        val type: Int = barcode.getValueType()
        map.putInt("type", type)
        when (type) {
            Barcode.TYPE_UNKNOWN, Barcode.TYPE_ISBN, Barcode.TYPE_TEXT -> map.putString(
                "data",
                barcode.getRawValue()
            )
            Barcode.TYPE_CONTACT_INFO -> {
                val contactInfo: Barcode.ContactInfo? = barcode.getContactInfo()
                if (contactInfo != null) {
                    map.putMap("data", convertToMap(contactInfo))
                }}
            Barcode.TYPE_EMAIL -> {
                val email: Barcode.Email? = barcode.getEmail()
                if (email != null) {
                    map.putMap("data", convertToMap(email))
                }}
            Barcode.TYPE_PHONE -> {
                val phone: Barcode.Phone? = barcode.getPhone()
                if (phone != null) {
                    map.putMap("data", convertToMap(phone))
                }}
            Barcode.TYPE_SMS -> {
                val sms: Barcode.Sms? = barcode.getSms()
                if (sms != null) {
                    map.putMap("data", convertToMap(sms))
                }}
            Barcode.TYPE_URL -> {
                val url: Barcode.UrlBookmark? = barcode.getUrl()
                if (url != null) {
                    map.putMap("data", convertToMap(url))
                }}
            Barcode.TYPE_WIFI -> {
                val wifi: Barcode.WiFi? = barcode.getWifi()
                if (wifi != null) {
                    map.putMap("data", convertToMap(wifi))
                }}
            Barcode.TYPE_GEO -> {
                val geoPoint: Barcode.GeoPoint? = barcode.getGeoPoint()
                if (geoPoint != null) {
                    map.putMap("data", convertToMap(geoPoint))
                }}
            Barcode.TYPE_CALENDAR_EVENT -> {
                val calendarEvent: Barcode.CalendarEvent? = barcode.getCalendarEvent()
                if (calendarEvent != null) {
                    map.putMap("data", convertToMap(calendarEvent))
                }}
            Barcode.TYPE_DRIVER_LICENSE -> {
                val driverLicense: Barcode.DriverLicense? = barcode.getDriverLicense()
                if (driverLicense != null) {
                    map.putMap("data", convertToMap(driverLicense))
                }}
        }
        return map
    }

    private fun convertBarcode(barcode: Barcode): WritableNativeMap {
        val map = WritableNativeMap()
        val boundingBox: Rect? = barcode.getBoundingBox()
        if (boundingBox != null) {
            map.putMap("boundingBox", convertToMap(boundingBox))
        }
        val cornerPoints: Array<Point>? = barcode.getCornerPoints()
        if (cornerPoints != null) {
            map.putArray("cornerPoints", convertToArray(cornerPoints))
        }
        val displayValue: String? = barcode.getDisplayValue()
        if (displayValue != null) {
            map.putString("displayValue", displayValue)
        }
        val rawValue: String? = barcode.getRawValue()
        if (rawValue != null) {
            map.putString("rawValue", rawValue)
        }
        map.putMap("content", convertContent(barcode))
        map.putInt("format", barcode.getFormat())
        Log.d("VisionCameraBarcodeScanner", "Barcode: " + barcode.getFormat())
        return map
    }

    companion object {
        private val barcodeFormats: HashSet<Int> = HashSet(
            Arrays.asList(
                Barcode.FORMAT_UNKNOWN,
                Barcode.FORMAT_ALL_FORMATS,
                Barcode.FORMAT_CODE_128,
                Barcode.FORMAT_CODE_39,
                Barcode.FORMAT_CODE_93,
                Barcode.FORMAT_CODABAR,
                Barcode.FORMAT_DATA_MATRIX,
                Barcode.FORMAT_EAN_13,
                Barcode.FORMAT_EAN_8,
                Barcode.FORMAT_ITF,
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_UPC_A,
                Barcode.FORMAT_UPC_E,
                Barcode.FORMAT_PDF417,
                Barcode.FORMAT_AZTEC
            )
        )
    }
}