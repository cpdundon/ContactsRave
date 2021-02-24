package com.example.contactsrave.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.contactsrave.model.constant.addressType.AddressType
import com.example.contactsrave.model.constant.addressType.EnumAddressType
import com.example.contactsrave.model.constant.addressType.Home
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity()
class Address @JvmOverloads constructor(
        var street_one: String?,
        var street_two: String?,
        var state: String?,
        var zip_code: String?,
        var type: EnumAddressType = EnumAddressType.Home,
        var isActive: Boolean = true
    ): Parcelable {
        @PrimaryKey(autoGenerate = true)
        var id: Long = -1
}
