package com.example.gren_usar.data

import androidx.annotation.StringRes
import com.example.gren_usar.R

object DataSource {
    fun loadCategories(): List<Categories> {
        return listOf<Categories>(
            Categories(R.string.Beverages,R.drawable.beverages),
            Categories(R.string.bread_bakery,R.drawable.bread_bakery),
            Categories(R.string.Vegetables,R.drawable.vegetables),
            Categories(R.string.Fruits,R.drawable.fruit),
            Categories(R.string.Egg,R.drawable.egg),
            Categories(R.string.Frozen_Veg,R.drawable.frozen_veg),
            Categories(R.string.Home_Care,R.drawable.homecare),
            Categories(R.string.Pet_Care,R.drawable.petcare)
        )
    }

    fun loadItems(
        @StringRes categoryName: Int,
    ) : List<Item> {
        return listOf(
            Item(R.string.Coca_Cola,R.drawable.coke,R.string.Beverages,2,0.74f),
            Item(R.string.Lemonade, R.drawable.lemonade,R.string.Beverages,1,0.15f),
            Item(R.string.Chocolate,R.drawable.chocolate,R.string.Beverages,25,0.36f),
            Item(R.string.Whisky,R.drawable.whisky,R.string.Beverages,25,0.65f),
            Item(R.string.Coco, R.drawable.coco,R.string.Beverages,25,0.52f),
            Item(R.string.Fruit_punch,R.drawable.fruitpunch,R.string.Beverages,25,0.20f)
        ).filter { categoryName ==it.categoryName }
    }
}