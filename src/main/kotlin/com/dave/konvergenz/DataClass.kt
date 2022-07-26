package com.dave.konvergenz

data class LoginResponse(
    val accessToken:String,
    val userId:String,
    val firstName:String,
    val emailAddress:String,
    val lastName:String,
    val phoneNumber:String,
    val roles:List<String>,
)
data class Results(
    val statusCode: Int,
    val details: Any
)
data class ErrorMessage(
    val error: String
)