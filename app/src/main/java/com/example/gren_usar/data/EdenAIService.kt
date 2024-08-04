package com.example.gren_usar.data

// Define data classes to represent the structure of the API response
data class EdenItem(
    val label: String,
    val confidence: Double,
    val x_min: Float,
    val x_max: Float,
    val y_min: Float,
    val y_max: Float
)

data class ApiResponse(
    val items: List<Item>,
    val status: String,
    val provider: String,
    val cost: Double
)

// Function to merge items from multiple API responses
fun mergeItems(responses: List<ApiResponse>): List<Item> {
    return responses.flatMap { it.items }
}
