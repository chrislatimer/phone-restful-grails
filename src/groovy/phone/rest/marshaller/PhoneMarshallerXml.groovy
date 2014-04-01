package phone.rest.marshaller

import grails.converters.XML
import org.codehaus.groovy.grails.web.converters.marshaller.ClosureObjectMarshaller
import phone.rest.Phone

/**
 * Created by clatimer on 3/28/2014.
 */
class PhoneMarshallerXml extends NamedMarshallerXml {

    static final marshal = { Phone phone, XML xml ->
        xml.attribute 'id', phone.id.toString()
        xml.build {
            name(phone.name)
        }
    }

    public PhoneMarshallerXml() {
        super(Phone, marshal)
    }
}
