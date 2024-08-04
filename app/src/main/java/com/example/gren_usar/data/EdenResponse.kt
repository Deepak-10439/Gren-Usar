package com.example.gren_usar.data

data class DetectionItem(
    val label: String,
    val confidence: Float,
    val x_min: Float,
    val x_max: Float,
    val y_min: Float,
    val y_max: Float
)

data class DetectionResponse(
    val items: List<DetectionItem>,
    val status: String,
    val provider: String,
    val cost: Float
)

data class LaunchExecutionResponse(
    val execution_id: String
)
