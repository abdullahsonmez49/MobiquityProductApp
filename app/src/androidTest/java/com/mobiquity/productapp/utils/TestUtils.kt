package com.mobiquity.productapp.utils

import com.mobiquity.productapp.data.Product
import com.mobiquity.productapp.data.SalePrice
import com.mobiquity.productapp.data.Section

val testSalesPrice1 = SalePrice("12","EUR")
val testSalesPrice2 = SalePrice("11","EUR")

val testProduct1 = Product("1", "36802", "Bread", "/Bread.jpg","Bread Descripton",testSalesPrice1)
val testProduct2 = Product("2", "36803", "Cola", "/Cola.jpg","Cola Descripton",testSalesPrice2)

val testProducts1 = arrayListOf(
    Product("3", "36802", "Bread", "/Bread.jpg","",testSalesPrice1),
    Product("4", "36802", "Sandwich", "/Sandwich.jpg","",testSalesPrice1),
    Product("5", "36802", "Milk", "/Milk.jpg","",testSalesPrice1),
)

val testProducts2 = arrayListOf(
    Product("3", "36803", "Cola", "/Cola.jpg","",testSalesPrice1),
    Product("4", "36803", "Fanta", "/Fanta.jpg","",testSalesPrice1),
    Product("5", "36803", "Juice", "/Juice.jpg","",testSalesPrice1),
)

val testSection1 = Section("36802","Food","Food Description", testProducts1)
val testSection2 = Section("36803","Drink","Drink Description", testProducts2)

val testSections = arrayListOf( testSection1, testSection2)
