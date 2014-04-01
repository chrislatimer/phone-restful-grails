package phone.rest.marshaller

import grails.converters.JSON
import org.codehaus.groovy.grails.web.converters.marshaller.ClosureObjectMarshaller
import phone.rest.Phone
import phone.rest.ProductVariation

/**
 * Created by clatimer on 3/28/2014.
 */
class PhoneMarshallerJsonCompact extends NamedMarshallerJson {

    static final marshal = { Phone phone ->
        def json = [:]
        json.id = phone.id
        json.name = phone.name
        if(phone.description) {
            json.description = phone.description
        }
        json
    }

    public PhoneMarshallerJsonCompact() {
        super(Phone, marshal)
    }
}
