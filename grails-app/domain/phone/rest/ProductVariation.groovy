package phone.rest

class ProductVariation {

    Boolean baseVariant
    String label
    BigDecimal listPrice
    BigDecimal salePrice

    static belongsTo = [product:Product]
    static constraints = {
    }
}
