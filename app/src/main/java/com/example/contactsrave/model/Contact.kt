package com.example.contactsrave.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity()
class Contact @JvmOverloads constructor(
            var first_name: String?,
            var last_name: String?,
            var eMail: String?
        ): Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}