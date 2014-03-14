package phone.rest

class Manufacturer {

    String name

    static hasMany = [products:Product]
    static constraints = {
    }
}
