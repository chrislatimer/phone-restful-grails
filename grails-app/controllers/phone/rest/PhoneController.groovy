package phone.rest

import phone.rest.command.ListCommand

class PhoneController {

    def index(ListCommand listCmd) {
        def json = [:]
        if(listCmd.hasErrors()) {
            response.status = 401
            json.errors = []
            listCmd.errors.fieldErrors.each {
                log.debug("Rejected value ${it.rejectedValue} for field ${it.field}")
                json.errors << "Rejected value ${it.rejectedValue} for field ${it.field}"
            }
        }
        else {
            def phones = Phone.list(max:params?.max ?: 10, offset:params?.offset ?: 0)
            json.phones = []
            phones.each {
                def phone = [:]
                phone.id = it.id
                phone.name = it.name
                phone.links = [[rel:"self", url:createLink(controller:"phone", action:"show", id:it.id)]]
                phone.variations = []
                it.productVariations.each {
                    phone.variations << [baseVariation:it.baseVariation, label:it.label, listPrice:it.listPrice, salePrice:it.salePrice]
                }
                json.phones << phone
            }
        }
        render(contentType: 'application/json') {
            json
        }
    }

    def show(Integer id) {

    }
}
