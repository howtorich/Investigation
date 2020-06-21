package com.example.investigation.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("ApiAction")
    val ApiAction:Int?,
    @SerializedName("chatRegisterUserModel")
    val chatRegisterUserModel:registerModel?
)
data class registerModel(
    @SerializedName("UserName")
    val UserName:String?
)

data class RegResponse(
    @SerializedName("chatRegisterUserOutput")
    val chatRegisterUserOutput:RegResp?,

    @SerializedName("chatRegisteredUsers")
    val chatRegisteredUsers:String?,

    @SerializedName("chatRegisteredUserFriends")
    val chatRegisteredUserFriends:String?,

    @SerializedName("responseModel")
    val responseModel:ResponseModelStatus?,

    @SerializedName("errorMessage")
    val errorMessage:String?,

    @SerializedName("innerException")
    val innerException:String?,

    @SerializedName("stackTrace")
    val stackTrace:String?,

    @SerializedName("executionalStatus")
    val executionalStatus:Int?,

    @SerializedName("executionalStatusMessage")
    val executionalStatusMessage:String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(RegResp::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(ResponseModelStatus::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(chatRegisterUserOutput,flags)
        parcel.writeString(chatRegisteredUsers)
        parcel.writeString(chatRegisteredUserFriends)
        parcel.writeParcelable(responseModel,flags)
        parcel.writeString(errorMessage)
        parcel.writeString(innerException)
        parcel.writeString(stackTrace)
        parcel.writeValue(executionalStatus)
        parcel.writeString(executionalStatusMessage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RegResponse> {
        override fun createFromParcel(parcel: Parcel): RegResponse {
            return RegResponse(parcel)
        }

        override fun newArray(size: Int): Array<RegResponse?> {
            return arrayOfNulls(size)
        }
    }
}

data class RegResp(
                @SerializedName("userName")
                val userName:String?,
                 @SerializedName("userId")
                 val userId:Long?
              ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userName)
        parcel.writeValue(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RegResp> {
        override fun createFromParcel(parcel: Parcel): RegResp {
            return RegResp(parcel)
        }

        override fun newArray(size: Int): Array<RegResp?> {
            return arrayOfNulls(size)
        }
    }
}

data class ResponseModelStatus(
    @SerializedName("executionalStatus")
    val executionalStatus:Int?,
    @SerializedName("errorStatus")
    val errorStatus:Int?,
    @SerializedName("errorMessage")
    val errorMessage:String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(executionalStatus)
        parcel.writeValue(errorStatus)
        parcel.writeString(errorMessage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResponseModelStatus> {
        override fun createFromParcel(parcel: Parcel): ResponseModelStatus {
            return ResponseModelStatus(parcel)
        }

        override fun newArray(size: Int): Array<ResponseModelStatus?> {
            return arrayOfNulls(size)
        }
    }
}