package me.mafu.dogslist_simplesurance.data.remote_data.dtos

data class BreedsDto(
    val status: String,
    val message: Map<String, List<String>>,
) {
    companion object {
        const val SUCCESS_STATUS = "success"
    }
}
