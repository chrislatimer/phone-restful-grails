package phone.rest

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PhoneController)
@Mock([Manufacturer, Phone])
class PhoneControllerTests extends Specification {

    def setup() {
        Manufacturer samsung = new Manufacturer(name:"Samsung")
        samsung.save(failOnError: true)

        Manufacturer apple = new Manufacturer(name: "Apple")
        apple.save(failOnError: true)

        Manufacturer htc = new Manufacturer(name: "HTC")
        htc.save(failOnError: true)

        Phone galaxyS4 = new Phone(name:"Galaxy S4")
        galaxyS4.manufacturer = samsung
        galaxyS4.save(failOnError: true)

        Phone galaxyS3 = new Phone(name:"Galaxy S3")
        galaxyS3.manufacturer = samsung
        galaxyS3.save(failOnError: true)
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
            response.json?.phones?.size() == 2
    }
}