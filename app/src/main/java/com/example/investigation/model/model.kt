package com.example.investigation.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("ApiAction")
    val ApiAction:Int?,

    @SerializedName("chatRegisterUserModel")
    val chatRegisterUserModel:RegResp?,

    @SerializedName("chatConversationModel")
    val chatConversationModel:ChatInputModel?
)
data class ChatInputModel(
    @SerializedName("Chat_bwt")
    val Chat_bwt:String?
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

    @SerializedName("userApplicationDetailsModel")
    val userDetailsModel:UserModel?,

    @SerializedName("chatConversationModel")
    val chatConversationModel:chatOutputModel?,

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


data class UserModel(
    @SerializedName("userName")
    val username:String?,

    @SerializedName("online")
    val online:Boolean?,

    @SerializedName("recentChatUsers")
    val recentChatUsers:String?

)

data class chatOutputModel(
    @SerializedName("chat_bwt")
    val chat_bwt:String?,

    @SerializedName("conversationId")
    val conversationId:Long?,

    @SerializedName("chatRequestByUserId")
    val chatRequestByUserId:Long?,

    @SerializedName("conversationMesgCount")
    val conversationMesgCount:Long?,

    @SerializedName("unreadMesgCount")
    val unreadMesgCount:Long?,

    @SerializedName("conversationType")
    val conversationType:Long?,

    @SerializedName("isActive")
    val isActive:Boolean?




)