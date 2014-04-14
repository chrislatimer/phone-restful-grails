package phone.rest.marshaller

import phone.rest.Manufacturer

/**
 * Created by chris on 4/13/14.
 */
class ManufacturerMarshallerJsonCompact  extends NamedMarshallerJson {
    static final marshal = { Manufacturer manufacturer ->
        def json = [:]
        json.id = manufacturer.id
        json.name = manufacturer.name
        json
    }

    public ManufacturerMarshallerJsonCompact() {
        super(Manufacturer, marshal)
    }
}
