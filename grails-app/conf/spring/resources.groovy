import grails.converters.JSON
import grails.converters.XML
import org.codehaus.groovy.grails.web.converters.configuration.ObjectMarshallerRegisterer
import phone.rest.Phone
import phone.rest.marshaller.NamedMarshallerJson
import phone.rest.marshaller.NamedMarshallerXml
import phone.rest.marshaller.PhoneMarshallerJson
import phone.rest.marshaller.PhoneMarshallerJsonCompact
import phone.rest.marshaller.PhoneMarshallerXml
import phone.rest.marshaller.configure.NamedMarshallerInitializer
import phone.rest.renderer.ApiRenderer

// Place your Spring DSL code here
beans = {
    customPhoneJsonMarshaller(PhoneMarshallerJson) {
        name = "complete"
    }

    customPhoneJsonMarshallerCompact(PhoneMarshallerJsonCompact) {
        name = "compact"
    }

    customPhoneXmlMarshaller(PhoneMarshallerXml) {
        name = "complete"
    }

    phoneRenderer(ApiRenderer, Phone)
}
