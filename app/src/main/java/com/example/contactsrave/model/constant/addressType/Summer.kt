package com.example.contactsrave.model.constant.addressType

import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity
class Summer (override val aType: String = "Summer") : AddressType(), Parcelable