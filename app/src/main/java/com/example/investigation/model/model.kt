package com.example.investigation.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("ApiAction")
    val ApiAction:Int?,
    @SerializedName("chatRegisterUserModel")
    val chatRegisterUserModel:RegResp?
)
//data class registerModel(
//    @SerializedName("UserName")
//    val UserName:String?
//)

data class RegResponse(
    @SerializedName("chatRegisterUserOutput")
    val chatRegisterUserOutput:RegResp?,

    @SerializedName("chatRegisteredUsers")
    val chatRegisteredUsers:String?,

    @SerializedName("friends")
    val chatRegisteredUserFriends:ArrayList<RegResp>?,

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
)
data class RegResp(
                @SerializedName("userName")
                val userName:String?,
                 @SerializedName("userId")
                 val userId:Long?
              )

data class ResponseModelStatus(

    @SerializedName("executionalStatus")
    val executionalStatus:Int?,

    @SerializedName("errorStatus")
    val errorStatus:Long?,

    @SerializedName("errorMessage")
    val errorMessage:String?

)

data class AddingFriend(
    @SerializedName("ApiAction")
    val ApiAction:Int?,

    @SerializedName("addFriend")
    val AddFrined:AddFriendModel?


)
data class AddFriendModel(

    @SerializedName("UserId")
    val userId:Long?,

    @SerializedName("AddingFriendUserId")
    val AddingFriendUserId:Long?
)