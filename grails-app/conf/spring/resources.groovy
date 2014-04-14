
import phone.rest.Phone
import phone.rest.marshaller.ManufacturerMarshallerJsonCompact
import phone.rest.marshaller.PhoneMarshallerJson
import phone.rest.marshaller.PhoneMarshallerJsonCompact
import phone.rest.marshaller.PhoneMarshallerXml
import phone.rest.renderer.ApiJsonCollectionRenderer
import phone.rest.renderer.ApiJsonRenderer

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

    customManufacturerMarshallerCompact(ManufacturerMarshallerJsonCompact) {
        name = "compact"
    }

    // currently our Manufacturer class is exceedingly simple so no need
    // for another marshaller
    customManufacturerMarshaller(ManufacturerMarshallerJsonCompact) {
        name = "complete"
    }

    phoneRenderer(ApiJsonRenderer, Phone) {
        label = "phone"
    }

    phoneCollectionRenderer(ApiJsonCollectionRenderer, Phone) {
        label = "phones"
    }
}
