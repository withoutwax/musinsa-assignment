package com.example.musinsa_assignment.datasource.mock

import org.springframework.stereotype.Repository
import com.example.musinsa_assignment.datasource.InventoryDataSource
import com.example.musinsa_assignment.model.Inventory
import kotlin.math.min

@Repository("mock")
class MockInventoryDataSource : InventoryDataSource {

    val inventories = mutableListOf(
        Inventory("A", 11200, 5500, 4200, 9000, 2000, 1700, 1800, 2300),
        Inventory("B", 10500, 5900, 3800, 9100, 2100, 2000, 2000, 2200),
        Inventory("C", 10000, 6200, 3300, 9200, 2200, 1900, 2200, 2100),
        Inventory("D", 10100, 5100, 3000, 9500, 2500, 1500, 2400, 2000),
        Inventory("E", 10700, 5000, 3800, 9900, 2300, 1800, 2100, 2100),
        Inventory("F", 11200, 7200, 4000, 9300, 2100, 1600, 2300, 1900),
        Inventory("G", 10500, 5800, 3900, 9000, 2200, 1700, 2100, 2000),
        Inventory("H", 10800, 6300, 3100, 9700, 2100, 1600, 2000, 2000),
        Inventory("I", 11400, 6700, 3200, 9500, 2400, 1700, 1700, 2400),
    )

    override fun retrieveInventories(): Collection<Inventory> = inventories

    override fun retrieveInventory(brandName: String): Inventory{
        return inventories.firstOrNull() { it.brandName == brandName }
            ?: throw NoSuchElementException("해당 브랜드를 찾을 수 없습니다.")
    }

    // 구현 1
    override fun getMinimumPriceForEachCategories(): MutableMap<String, MutableMap<String, String>> {
        val minCategory = mutableMapOf(
            "top" to mutableMapOf(
                "brand_name" to inventories[0].brandName,
                "price" to inventories[0].top.toString()
            ),
            "outer" to mutableMapOf(
                "brand_name" to inventories[0].brandName,
                "price" to inventories[0].outer.toString()
            ),
            "trouser" to mutableMapOf(
                "brand_name" to inventories[0].brandName,
                "price" to inventories[0].trouser.toString()
            ),
            "sneaker" to mutableMapOf(
                "brand_name" to inventories[0].brandName,
                "price" to inventories[0].sneaker.toString()
            ),
            "bag" to mutableMapOf(
                "brand_name" to inventories[0].brandName,
                "price" to inventories[0].bag.toString()
            ),
            "hat" to mutableMapOf(
                "brand_name" to inventories[0].brandName,
                "price" to inventories[0].hat.toString()
            ),
            "sock" to mutableMapOf(
                "brand_name" to inventories[0].brandName,
                "price" to inventories[0].sock.toString()
            ),
            "accessory" to mutableMapOf(
                "brand_name" to inventories[0].brandName,
                "price" to inventories[0].accessory.toString()
            ),
        )

        for ((i, item) in inventories.withIndex()) {
            if (i == 0) continue

            if (item.top <= minCategory["top"]?.get("price")?.toInt()!!) {
                minCategory["top"]?.set("brand_name", item.brandName)
                minCategory["top"]?.set("price", item.top.toString())
            }
            if (item.outer <= minCategory["outer"]?.get("price")?.toInt()!!) {
                minCategory["outer"]?.set("brand_name", item.brandName)
                minCategory["outer"]?.set("price", item.outer.toString())
            }
            if (item.trouser <= minCategory["trouser"]?.get("price")?.toInt()!!) {
                minCategory["trouser"]?.set("brand_name", item.brandName)
                minCategory["trouser"]?.set("price", item.trouser.toString())
            }
            if (item.sneaker <= minCategory["sneaker"]?.get("price")?.toInt()!!) {
                minCategory["sneaker"]?.set("brand_name", item.brandName)
                minCategory["sneaker"]?.set("price", item.sneaker.toString())
            }
            if (item.bag <= minCategory["bag"]?.get("price")?.toInt()!!) {
                minCategory["bag"]?.set("brand_name", item.brandName)
                minCategory["bag"]?.set("price", item.bag.toString())
            }
            if (item.hat <= minCategory["hat"]?.get("price")?.toInt()!!) {
                minCategory["hat"]?.set("brand_name", item.brandName)
                minCategory["hat"]?.set("price", item.hat.toString())
            }
            if (item.sock <= minCategory["sock"]?.get("price")?.toInt()!!) {
                minCategory["sock"]?.set("brand_name", item.brandName)
                minCategory["sock"]?.set("price", item.sock.toString())
            }
            if (item.accessory <= minCategory["accessory"]?.get("price")?.toInt()!!) {
                minCategory["accessory"]?.set("brand_name", item.brandName)
                minCategory["accessory"]?.set("price", item.accessory.toString())
            }
        }
        return minCategory;
    }

    // 구현 2
    override fun getCheapestBrandForAllProduct() : Map<String, Any> {
        val cheapestBrand = mutableMapOf(
            "brand_name" to inventories[0].brandName,
            "total" to (inventories[0].top + inventories[0].outer + inventories[0].trouser + inventories[0].sneaker + inventories[0].bag + inventories[0].hat + inventories[0].sock + inventories[0].accessory).toString()
        )

        for ((i, item) in inventories.withIndex()) {
            if (i == 0) continue

            val sum = item.top + item.outer + item.trouser + item.sneaker + item.bag + item.hat + item.sock + item.accessory;

            if (sum <= cheapestBrand["total"]?.toInt()!!) {
                cheapestBrand["brand_name"] = item.brandName;
                cheapestBrand["total"] = sum.toString()
            }
        }

        val cheapest = inventories.firstOrNull() { it.brandName == cheapestBrand.get("brand_name") }

        val returnValue = mapOf(
            "최저가" to mapOf(
                "브랜드" to cheapest!!.brandName,
                "카테고리" to listOf(
                    mapOf(
                        "카테고리" to "상의",
                        "가격" to cheapest.top.toString()
                    ),
                    mapOf(
                        "카테고리" to "아우터",
                        "가격" to cheapest.outer.toString()
                    ),
                    mapOf(
                        "카테고리" to "바지",
                        "가격" to cheapest.trouser.toString()
                    ),
                    mapOf(
                        "카테고리" to "스니커즈",
                        "가격" to cheapest.sneaker.toString()
                    ),
                    mapOf(
                        "카테고리" to "가방",
                        "가격" to cheapest.bag.toString()
                    ),
                    mapOf(
                        "카테고리" to "모자",
                        "가격" to cheapest.hat.toString()
                    ),
                    mapOf(
                        "카테고리" to "양말",
                        "가격" to cheapest.sock.toString()
                    ),
                    mapOf(
                        "카테고리" to "액세서리",
                        "가격" to cheapest.accessory.toString()
                    ),
                ),
                "총액" to cheapestBrand["total"]
            )
        )

        return returnValue;
    }

    // 구현 3
    override fun getMinMaxForEachCategories(category: String): Map<String, Any> {
        var min_brand_name = inventories[0].brandName;
        var min_price = Int.MAX_VALUE;
        var max_brand_name = inventories[0].brandName;
        var max_price = Int.MIN_VALUE;

        for (item in inventories) {
            if (category == "top") {
                if (item.top <= min_price) {
                    min_brand_name = item.brandName
                    min_price = item.top
                }
                if (item.top >= max_price) {
                    max_brand_name = item.brandName
                    max_price = item.top
                }
            }
            if (category == "outer") {
                if (item.outer <= min_price) {
                    min_brand_name = item.brandName
                    min_price = item.outer
                }
                if (item.outer >= max_price) {
                    max_brand_name = item.brandName
                    max_price = item.outer
                }
            }
            if (category == "trouser") {
                if (item.trouser <= min_price) {
                    min_brand_name = item.brandName
                    min_price = item.trouser
                }
                if (item.trouser >= max_price) {
                    max_brand_name = item.brandName
                    max_price = item.trouser
                }
            }
            if (category == "sneaker") {
                if (item.sneaker <= min_price) {
                    min_brand_name = item.brandName
                    min_price = item.sneaker
                }
                if (item.sneaker >= max_price) {
                    max_brand_name = item.brandName
                    max_price = item.sneaker
                }
            }
            if (category == "bag") {
                if (item.bag <= min_price) {
                    min_brand_name = item.brandName
                    min_price = item.bag
                }
                if (item.bag >= max_price) {
                    max_brand_name = item.brandName
                    max_price = item.bag
                }
            }
            if (category == "hat") {
                if (item.hat <= min_price) {
                    min_brand_name = item.brandName
                    min_price = item.hat
                }
                if (item.hat >= max_price) {
                    max_brand_name = item.brandName
                    max_price = item.hat
                }
            }
            if (category == "sock") {
                if (item.sock <= min_price) {
                    min_brand_name = item.brandName
                    min_price = item.sock
                }
                if (item.sock >= max_price) {
                    max_brand_name = item.brandName
                    max_price = item.sock
                }
            }
            if (category == "accessory") {
                if (item.accessory <= min_price) {
                    min_brand_name = item.brandName
                    min_price = item.accessory
                }
                if (item.accessory >= max_price) {
                    max_brand_name = item.brandName
                    max_price = item.accessory
                }
            }
        }

        var selectedCategory = ""

        if (category == "top") {
            selectedCategory = "상의"
        } else if (category == "outer") {
            selectedCategory = "아우터"
        } else if (category == "trouser") {
            selectedCategory = "바지"
        } else if (category == "sneaker") {
            selectedCategory = "스니커즈"
        } else if (category == "bag") {
            selectedCategory = "가방"
        } else if (category == "hat") {
            selectedCategory = "모자"
        } else if (category == "sock") {
            selectedCategory = "양말"
        } else if (category == "accessory") {
            selectedCategory = "악세사리"
        }

        return mapOf(
            "카테고리" to selectedCategory,
            "최저가" to listOf(
                mapOf("브랜드" to min_brand_name, "가격" to min_price)
            ),
            "최고가" to listOf(
                mapOf("브랜드" to max_brand_name, "가격" to max_price)
            )
        )
    }

    // 구현 4
    override fun createInventory(inventory: Inventory): Inventory {
        if (inventories.any { it.brandName == inventory.brandName }) {
            throw IllegalArgumentException("해당 브랜드가 이미 존재합니다: ${inventory.brandName}");
        }
        inventories.add(inventory)

        return inventory
    }

    override fun updateInventory(inventory: Inventory): Inventory {
        val currentInventory = inventories.firstOrNull { it.brandName == inventory.brandName }
            ?: throw NoSuchElementException("해당 브랜드를 찾지 못하였습니다: ${inventory.brandName}")

        inventories.remove(currentInventory)
        inventories.add(inventory)

        return inventory
    }

    override fun deleteInventory(brandName: String): Inventory {
        val currentInventory = inventories.firstOrNull { it.brandName == brandName }
            ?: throw NoSuchElementException("해당 브랜드를 찾을 수 없습니다.")

        inventories.remove(currentInventory)

        return currentInventory
    }
}

