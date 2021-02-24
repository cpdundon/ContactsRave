package com.example.contactsrave.model.constant.addressType

import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity
open class AddressType (open val aType: String = "parent") : Parcelable {}