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
        samsung.addToProducts(new Phone(name:"Galaxy S4"))
        samsung.addToProducts(new Phone(name:"Galaxy S3"))
        samsung.save(failOnError: true)

        Manufacturer apple = new Manufacturer(name: "Apple")
        Phone iPhone5s = new Phone(name:"iPhone 5s")
        iPhone5s.addToProductVariations([baseVariant: true, label: "16GB", listPrice: 648.00, salePrice: 648.00])
        iPhone5s.addToProductVariations([baseVariant: true, label: "32GB", listPrice: 748.00, salePrice: 748.00])
        iPhone5s.addToProductVariations([baseVariant: true, label: "64GB", listPrice: 848.00, salePrice: 848.00])
        apple.addToProducts(iPhone5s)
        apple.save(failOnError: true)

        Manufacturer htc = new Manufacturer(name: "HTC")
        htc.save(failOnError: true)
    }

    def cleanup() {
    }

    void "Request to /phones produces a valid response"() {
        when:
            controller.index()

        then:
            response.status == 200
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

    void "Each phones self link should match the RESTful pattern /phones/id"() {
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
}