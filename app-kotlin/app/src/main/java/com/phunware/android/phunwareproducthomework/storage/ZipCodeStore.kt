package com.phunware.android.phunwareproducthomework.storage

interface ZipCodeStore {
    fun getZipCodes(): List<String>
    fun addZipCode(zipCode: String)
}