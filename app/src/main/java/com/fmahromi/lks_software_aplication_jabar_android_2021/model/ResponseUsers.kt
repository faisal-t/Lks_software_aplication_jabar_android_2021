package com.fmahromi.lks_software_aplication_jabar_android_2021.model

data class ResponseUsers(
	val data: Data? = null,
	val message: String? = null,
	val status: Boolean? = null
)

data class Data(
	val password: String? = null,
	val id: String? = null,
	val username: String? = null
)

