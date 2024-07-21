package com.example.gren_usar.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Item (
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val categoryName: Int,
    val Price:Int,
    val footPrint:Float
    )