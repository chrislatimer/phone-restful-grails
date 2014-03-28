package phone.rest.marshaller

import org.codehaus.groovy.grails.web.converters.marshaller.ClosureObjectMarshaller
import phone.rest.Phone

/**
 * Created by chris on 3/17/14.
 */
class PhoneMarshallerJson extends ClosureObjectMarshaller<Phone> {

    static final marshal = { phone ->
        def json = [:]
        json.id = phone.id
        json.name = phone.name
        json
    }

    public PhoneMarshallerJson() {
        super(Phone, marshal)
    }

}
