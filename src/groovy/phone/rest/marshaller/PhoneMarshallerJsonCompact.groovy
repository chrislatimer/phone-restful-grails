package phone.rest.marshaller

import grails.converters.JSON
import org.codehaus.groovy.grails.web.converters.marshaller.ClosureObjectMarshaller
import phone.rest.Phone
import phone.rest.ProductVariation

/**
 * Created by clatimer on 3/28/2014.
 */
class PhoneMarshallerJsonCompact extends ClosureObjectMarshaller<JSON> {
    static final marshal = { Phone phone ->
        def json = [:]
        json.id = phone.id
        json.name = phone.name
        if(phone.description) {
            json.description = phone.description
        }
        json.productVariations = []
        phone.productVariations.each { ProductVariation v ->
            def variation = [:]
            variation.baseVariation = v.baseVariation
            variation.label = v.label
            variation.listPrice = v.listPrice
            variation.salePrice = v.salePrice
            json.productVariations << variation
        }
        json
    }

    public PhoneMarshallerJsonCompact() {
        super(Phone, marshal)
    }
}
