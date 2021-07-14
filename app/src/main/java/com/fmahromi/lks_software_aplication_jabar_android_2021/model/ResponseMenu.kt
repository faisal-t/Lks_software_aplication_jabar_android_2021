package com.fmahromi.lks_software_aplication_jabar_android_2021.model

data class ResponsePost<T>(
	var data: T? = null,
	var message: String? = null
)

data class ResponseMenu(
	val data: List<DataItem?>? = null
)

data class DataItem(
	val id : String? = null,
	val price: String? = null,
	val name: String? = null,
	val description: String? = null
)

