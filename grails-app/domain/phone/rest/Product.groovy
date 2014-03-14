package phone.rest

class Product {

    String name
    static hasMany = [productVariations:ProductVariation, images:Image]
    static belongsTo = [manufacturer:Manufacturer]
    static constraints = {
    }
}
