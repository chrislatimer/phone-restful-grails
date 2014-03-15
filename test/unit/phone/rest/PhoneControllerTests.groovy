package phone.rest

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PhoneController)
@Mock([Manufacturer, Phone, Product, ProductVariation])
class PhoneControllerTests extends Specification {

    def setup() {
        Manufacturer samsung = new Manufacturer(name:"Samsung")
        Phone galaxyS4 = new Phone(name:"Galaxy S4")
        galaxyS4.addToProductVariations([baseVariation: true, listPrice: 599.00, salePrice: 549.00])
        samsung.addToProducts(galaxyS4)
        Phone galaxyS3 = new Phone(name:"Galaxy S3")
        galaxyS3.addToProductVariations([baseVariation: true, listPrice: 399.00, salePrice: 349.00])
        samsung.addToProducts(galaxyS3)
        samsung.save(failOnError: true)

        Manufacturer apple = new Manufacturer(name: "Apple")
        Phone iPhone5s = new Phone(name:"iPhone 5s")
        iPhone5s.addToProductVariations([baseVariation: true, label: "16GB", listPrice: 648.00, salePrice: 648.00])
        iPhone5s.addToProductVariations([baseVariation: false, label: "32GB", listPrice: 748.00, salePrice: 748.00])
        iPhone5s.addToProductVariations([baseVariation: false, label: "64GB", listPrice: 848.00, salePrice: 848.00])
        apple.addToProducts(iPhone5s)
        apple.save(failOnError: true)
    }

    def cleanup() {
    }

    void "Request to /phones produces a valid response"() {
        when:
            controller.index()

        then:
            response.status == 200
    }

    void "Request to /phones produces a json status value of 'ok'"() {
        when:
            controller.index()

        then:
            response.json?.status == "ok"
    }

    void "Response produces json content type"() {
        when:
            controller.index()

        then:
            response.contentType ==~ /${JSON_CONTENT_TYPE}.*/
    }

    void "Invalid max value produces 401"() {
        when:
            params.max = 'QPR'
            controller.index()

        then:
            response.status == 401
    }

    void "Invalid max value produces json errors"() {
        when:
            params.max = 'QPR'
            controller.index()

        then:
            response.json?.errors?.size() == 1
    }

    void "Invalid offset value produces 401"() {
        when:
            params.offset = 'QPR'
            controller.index()

        then:
            response.status == 401
    }

    void "Invalid requests should produce a status of 'error'"() {
        when:
            params.offset = 'QPR'
            controller.index()

        then:
            response.json?.status == "error"
    }

    void "No params should return a list of phones"() {
        when:
            controller.index()

        then:
            response.json?.phones?.size() == 3
    }

    void "Max and offset should affect result size"() {
        when:
            params.max = 1
            params.offset = 0
            controller.index()

        then:
            response.json?.phones?.size() == 1
    }

    void "Phones should have a name attribute"() {
        when:
            controller.index()

        then:
            response.json?.phones?.size() > 0
            response.json?.phones?.each {
                assert it.name
            }
    }

    void "Phones should have an id attribute"() {
        when:
            controller.index()

        then:
            response.json?.phones?.size() > 0
            response.json?.phones?.each {
                assert it.id
            }
    }

    void "Phones should have a self link"() {
        when:
            controller.index()

        then:
            response.json?.phones?.size() > 0
            response.json?.phones?.each {
            assert it.links?.find {
                    it.rel == "self"
                }
            }
    }

    void "Each phone's self link should match the RESTful pattern /phones/id"() {
        when:
            controller.index()

        then:
            response.json?.phones?.size() > 0
            response.json?.phones?.each {
            def selfLink = it.links?.find {
                it.rel == "self"
            }
            assert selfLink.url.endsWith("/phones/${it.id}")
        }
    }

    void "Each phone should have at least one variation"() {
        when:
            controller.index()

        then:
            response.json.phones?.size() > 0
            response.json.phones?.each {
                assert it.variations?.size() > 0
            }
    }

    void "Each phone should have exactly one base variation"() {
        when:
            controller.index()

        then:
            response.json.phones?.size() > 0
            response.json.phones?.each {
                assert it.variations.findAll { it.baseVariation as Boolean }.size() == 1
            }
    }

    void "When retrieving a phone that doesn't exist it should return a 404"() {
        when:
            controller.show(4444)
        then:
            response.status == 404
    }

    void "A phone should have an id"() {
        given:
            def phone = Phone.list(max:1)[0]

        when:
            controller.show(phone.id)

        then:
            response.json.id
    }

    void "A phone should have a name"() {
        given:
        def phone = Phone.list(max:1)[0]

        when:
        controller.show(phone.id)

        then:
        response.json.name
    }

    void "A phone should have a self link"() {
        given:
        def phone = Phone.list(max:1)[0]

        when:
        controller.show(phone.id)

        then:
        response.json.links?.find{ it.rel == "self" }
    }

    void "A phone should have at least one variation"() {
        given:
        def phone = Phone.list(max:1)[0]

        when:
        controller.show(phone.id)

        then:
        response.json.variations?.size() > 0
    }
}