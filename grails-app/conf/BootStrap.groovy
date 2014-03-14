import grails.converters.JSON
import phone.rest.Phone

class BootStrap {

    def init = { servletContext ->

        JSON.createNamedConfig('minimal') {
            it.registerObjectMarshaller(Phone) {
                def map = [:]
                map['id'] = it.id
                map['name'] = it.name
                return map
            }
        }
    }
    def destroy = {
    }
}
