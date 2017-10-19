package shoppingcart.Products

import shoppingcart.Category.Category
import shoppingcart.Category.Stock


class Products {

    String imageName
    String productName
    int serialNumber
    Date cameInProducts
    Category category
    Stock stock

    static constraints = {
    }
    static mapping = {
//        version false
//        datasource 'shoppingcart'
    }
}
