package phone.rest.marshaller

import grails.converters.JSON

/**
 * Created by chris on 3/28/14.
 */
class NamedMarshallerJson extends NamedMarshaller {

    public NamedMarshallerJson(Class clazz, Closure closure) {
        super(JSON, clazz, closure)
    }
}
