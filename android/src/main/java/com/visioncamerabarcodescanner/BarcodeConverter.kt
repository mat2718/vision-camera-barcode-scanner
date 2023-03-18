package com.visioncamerabarcodescanner

import android.graphics.Point
import android.graphics.Rect
import com.facebook.react.bridge.WritableNativeArray
import com.facebook.react.bridge.WritableNativeMap
import com.google.mlkit.vision.barcode.common.Barcode
import java.util.List
import android.util.Log

/**
 * Converter util class used to convert Barcode related variables to either WritableNativeArray or
 * WritableNativeMap
 */
object BarcodeConverter {
    fun convertToArray(points: Array<Point>): WritableNativeArray {
        val array = WritableNativeArray()
        if (points == null) {
            Log.e("VisionCameraBarcodeScanner", "Points array is null")
            return array
        }
        for (point in points) {
            array.pushMap(convertToMap(point))
        }
        Log.d("VisionCameraBarcodeScanner", "Points array is not null")
        return array
    }

    fun convertToArray(elements: Array<String?>): WritableNativeArray {
        val array = WritableNativeArray()
        if (elements == null) {
            Log.e("VisionCameraBarcodeScanner", "Elements array is null")
            return array
        }
        for (elem in elements) {
            array.pushString(elem)
        }
        Log.d("VisionCameraBarcodeScanner", "Elements array is not null")
        return array
    }

    fun convertStringList(elements: MutableList<String?>): WritableNativeArray {
        val array = WritableNativeArray()
        if (elements == null) {
            Log.e("VisionCameraBarcodeScanner", "Elements array is null")
            return array
        }
        for (elem in elements) {
            array.pushString(elem)
        }
        Log.d("VisionCameraBarcodeScanner", "Elements array is not null")
        return array
    }

    fun convertAddressList(addresses: MutableList<Barcode.Address>): WritableNativeArray {
        val array = WritableNativeArray()

        for (address in addresses) {
            array.pushMap(convertToMap(address))
        }
        Log.d("VisionCameraBarcodeScanner", "Addresses array is not null")
        return array
    }

    fun convertPhoneList(phones: MutableList<Barcode.Phone>): WritableNativeArray {
        val array = WritableNativeArray()
        for (phone in phones) {
            array.pushMap(convertToMap(phone))
        }
        Log.d("VisionCameraBarcodeScanner", "Phones array is not null")
        return array
    }

    fun convertEmailList(emails: MutableList<Barcode.Email>): WritableNativeArray {
        val array = WritableNativeArray()
        for (email in emails) {
            array.pushMap(convertToMap(email))
        }
        Log.d("VisionCameraBarcodeScanner", "Emails array is not null")
        return array
    }

    fun convertToMap(point: Point): WritableNativeMap {
        val map = WritableNativeMap()
        map.putInt("x", point.x)
        map.putInt("y", point.y)
        Log.d("VisionCameraBarcodeScanner", "Point is not null")
        return map
    }

    fun convertToMap(address: Barcode.Address): WritableNativeMap {
        val map = WritableNativeMap()
        map.putArray("addressLines", convertToArray(address.getAddressLines()))
        map.putInt("type", address.getType())
        Log.d("VisionCameraBarcodeScanner", "Address is not null")
        return map
    }

    fun convertToMap(rect: Rect): WritableNativeMap {
        val map = WritableNativeMap()
        map.putInt("bottom", rect.bottom)
        map.putInt("left", rect.left)
        map.putInt("right", rect.right)
        map.putInt("top", rect.top)
        map.putInt("width", rect.width())
        map.putInt("height", rect.height())
        map.putDouble("x", rect.exactCenterX().toDouble())
        map.putDouble("y", rect.exactCenterY().toDouble())
        map.putInt("boundingBoxCenterX", rect.centerX())
        map.putInt("boundingBoxCenterY", rect.centerY())
        Log.d("VisionCameraBarcodeScanner", "Rect is not null")
        return map
    }

    fun convertToMap(contactInfo: Barcode.ContactInfo): WritableNativeMap {
        val map = WritableNativeMap()
        map.putArray("addresses", convertAddressList(contactInfo.getAddresses()))
        map.putArray("emails", convertEmailList(contactInfo.getEmails()))
        map.putMap("name", convertToMap(contactInfo.getName()))
        map.putString("organization", contactInfo.getOrganization())
        map.putArray("phones", convertPhoneList(contactInfo.getPhones()))
        map.putString("title", contactInfo.getTitle())
        map.putArray("urls", convertStringList(contactInfo.getUrls()))
        Log.d("VisionCameraBarcodeScanner", "ContactInfo is not null")
        return map
    }

    fun convertToMap(name: Barcode.PersonName?): WritableNativeMap {
        val map = WritableNativeMap()
        if (name == null) {
            return map
        }
        map.putString("first", name.getFirst())
        map.putString("formattedName", name.getFormattedName())
        map.putString("last", name.getLast())
        map.putString("middle", name.getMiddle())
        map.putString("prefix", name.getPrefix())
        map.putString("pronunciation", name.getPronunciation())
        map.putString("suffix", name.getSuffix())
        Log.d("VisionCameraBarcodeScanner", "Name is not null")
        return map
    }

    fun convertToMap(url: Barcode.UrlBookmark): WritableNativeMap {
        val map = WritableNativeMap()
        map.putString("title", url.getTitle())
        map.putString("url", url.getUrl())
        Log.d("VisionCameraBarcodeScanner", "Url is not null")
        return map
    }

    fun convertToMap(email: Barcode.Email): WritableNativeMap {
        val map = WritableNativeMap()
        map.putString("address", email.getAddress())
        map.putString("body", email.getBody())
        map.putString("subject", email.getSubject())
        map.putInt("type", email.getType())
        Log.d("VisionCameraBarcodeScanner", "Email is not null")
        return map
    }

    fun convertToMap(phone: Barcode.Phone): WritableNativeMap {
        val map = WritableNativeMap()
        map.putString("number", phone.getNumber())
        map.putInt("type", phone.getType())
        Log.d("VisionCameraBarcodeScanner", "Phone is not null")
        return map
    }

    fun convertToMap(sms: Barcode.Sms): WritableNativeMap {
        val map = WritableNativeMap()
        map.putString("message", sms.getMessage())
        map.putString("phoneNumber", sms.getPhoneNumber())
        Log.d("VisionCameraBarcodeScanner", "Sms is not null")
        return map
    }

    fun convertToMap(wifi: Barcode.WiFi): WritableNativeMap {
        val map = WritableNativeMap()
        map.putInt("encryptionType", wifi.getEncryptionType())
        map.putString("password", wifi.getPassword())
        map.putString("ssid", wifi.getSsid())
        Log.d("VisionCameraBarcodeScanner", "Wifi is not null")
        return map
    }

    fun convertToMap(geoPoint: Barcode.GeoPoint): WritableNativeMap {
        val map = WritableNativeMap()
        map.putDouble("lat", geoPoint.getLat())
        map.putDouble("lng", geoPoint.getLng())
        Log.d("VisionCameraBarcodeScanner", "GeoPoint is not null")
        return map
    }

    fun convertToMap(calendarDateTime: Barcode.CalendarDateTime?): WritableNativeMap {
        val map = WritableNativeMap()
        if (calendarDateTime == null) {
            Log.d("VisionCameraBarcodeScanner", "CalendarDateTime is null")
            return map
        }
        map.putInt("day", calendarDateTime.getDay())
        map.putInt("hours", calendarDateTime.getHours())
        map.putInt("minutes", calendarDateTime.getMinutes())
        map.putInt("month", calendarDateTime.getMonth())
        map.putString("rawValue", calendarDateTime.getRawValue())
        map.putInt("year", calendarDateTime.getYear())
        map.putInt("seconds", calendarDateTime.getSeconds())
        map.putBoolean("isUtc", calendarDateTime.isUtc())
        Log.d("VisionCameraBarcodeScanner", "CalendarDateTime is not null")
        return map
    }

    fun convertToMap(calendarEvent: Barcode.CalendarEvent): WritableNativeMap {
        val map = WritableNativeMap()
        map.putString("description", calendarEvent.getDescription())
        map.putMap("end", convertToMap(calendarEvent.getEnd()))
        map.putString("location", calendarEvent.getLocation())
        map.putString("organizer", calendarEvent.getOrganizer())
        map.putMap("start", convertToMap(calendarEvent.getStart()))
        map.putString("status", calendarEvent.getStatus())
        map.putString("summary", calendarEvent.getSummary())
        Log.d("VisionCameraBarcodeScanner", "CalendarEvent is not null")
        return map
    }

    fun convertToMap(driverLicense: Barcode.DriverLicense): WritableNativeMap {
        val map = WritableNativeMap()
        map.putString("addressCity", driverLicense.getAddressCity())
        map.putString("addressState", driverLicense.getAddressState())
        map.putString("addressStreet", driverLicense.getAddressStreet())
        map.putString("addressZip", driverLicense.getAddressZip())
        map.putString("birthDate", driverLicense.getBirthDate())
        map.putString("documentType", driverLicense.getDocumentType())
        map.putString("expiryDate", driverLicense.getExpiryDate())
        map.putString("firstName", driverLicense.getFirstName())
        map.putString("gender", driverLicense.getGender())
        map.putString("issueDate", driverLicense.getIssueDate())
        map.putString("issuingCountry", driverLicense.getIssuingCountry())
        map.putString("lastName", driverLicense.getLastName())
        map.putString("licenseNumber", driverLicense.getLicenseNumber())
        map.putString("middleName", driverLicense.getMiddleName())
        Log.d("VisionCameraBarcodeScanner", "DriverLicense is not null")
        return map
    }
}