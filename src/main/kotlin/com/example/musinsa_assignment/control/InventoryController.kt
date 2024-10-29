package com.example.musinsa_assignment.control

import com.example.musinsa_assignment.model.Inventory
import com.example.musinsa_assignment.service.InventoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/inventories")
class InventoryController(private val service: InventoryService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getInventories(): Collection<Inventory> = service.getInventories()

    @GetMapping("/min")
    fun getMinimumPriceForEachCategories(): MutableMap<String, MutableMap<String, String>> = service.getMinimumPriceForEachCategories()

    @GetMapping(value = ["/cheapest"], produces = ["application/json; charset=utf-8"])
    fun getCheapestBrandForAllProduct(): Map<String, Any> = service.getCheapestBrandForAllProduct()

    @GetMapping(value = ["/min-max-category/{category}"], produces = ["application/json; charset=utf-8"])
    fun getMinMaxForEachCategories(@PathVariable category: String): Map<String, Any> = service.getMinMaxForEachCategories(category)

    @PostMapping("/add")
    fun addInventory(@RequestBody body : Inventory) : Inventory {
        println("전달받은 값은 ${body.brandName} 입니다.")
        return service.addInventory(body)
    }

    @PutMapping("/update")
    fun updateInventory(@RequestBody body: Inventory): Inventory {
        println("전달받은 값은 ${body.brandName} 입니다.")
        return service.updateInventory(body)
    }

    @DeleteMapping("/delete/{brandName}")
    fun deleteInventory(@PathVariable brandName: String): Inventory {
        return service.deleteInventory(brandName)
    }

    @GetMapping("/{brandName}")
    fun getInventory(@PathVariable brandName: String): Inventory = service.getInventory(brandName)

    @GetMapping("/test")
    fun test(): String = "테스트 입니다"
}