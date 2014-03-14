package phone.rest

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PhoneController)
class PhoneControllerTests extends Specification {

    def setup() {
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
}