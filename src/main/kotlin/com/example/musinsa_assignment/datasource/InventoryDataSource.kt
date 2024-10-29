package com.example.musinsa_assignment.datasource

import com.example.musinsa_assignment.model.Inventory

interface InventoryDataSource {

    fun retrieveInventories(): Collection<Inventory>

    fun retrieveInventory(brandName: String): Inventory

    fun getMinimumPriceForEachCategories(): MutableMap<String, MutableMap<String, String>>

    fun getCheapestBrandForAllProduct() : Map<String, Any>

    fun getMinMaxForEachCategories(category: String): Map<String, Any>

    fun createInventory(inventory: Inventory): Inventory

    fun updateInventory(inventory: Inventory): Inventory

    fun deleteInventory(brandName: String): Inventory

}