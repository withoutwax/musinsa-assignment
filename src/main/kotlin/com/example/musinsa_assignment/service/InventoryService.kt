package com.example.musinsa_assignment.service

import com.example.musinsa_assignment.datasource.InventoryDataSource
import com.example.musinsa_assignment.model.Inventory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class InventoryService(@Qualifier("mock") private val dataSource: InventoryDataSource) {

    fun getInventories(): Collection<Inventory> = dataSource.retrieveInventories()

    fun getInventory(brandName: String): Inventory = dataSource.retrieveInventory(brandName)

    fun getMinimumPriceForEachCategories(): MutableMap<String, MutableMap<String, String>> = dataSource.getMinimumPriceForEachCategories()

    fun getCheapestBrandForAllProduct(): Map<String, Any> = dataSource.getCheapestBrandForAllProduct()

    fun getMinMaxForEachCategories(category: String): Map<String, Any> = dataSource.getMinMaxForEachCategories(category)

    fun addInventory(inventory: Inventory): Inventory = dataSource.createInventory(inventory)

    fun updateInventory(inventory: Inventory): Inventory = dataSource.updateInventory(inventory)

    fun deleteInventory(brandName: String): Inventory = dataSource.deleteInventory(brandName)

}