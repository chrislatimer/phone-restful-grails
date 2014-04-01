package phone.rest.marshaller

import grails.converters.XML

/**
 * Created by chris on 3/28/14.
 */
class NamedMarshallerXml extends NamedMarshaller {

    public NamedMarshallerXml(Class clazz, Closure closure) {
        super(XML, clazz, closure)
    }
}
