package me.mafu.dogslist_simplesurance.data.remote_data.dtos

data class BreedsImageDto(
    val status: String,
    val message: List<String>,
) {
    companion object {
        const val SUCCESS_STATUS = "success"
    }
}
