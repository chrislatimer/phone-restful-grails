package phone.rest

class ProductVariation {

    Boolean baseVariation
    String label
    BigDecimal listPrice
    BigDecimal salePrice

    static belongsTo = [product:Product]
    static constraints = {
        label nullable: true
    }
}
