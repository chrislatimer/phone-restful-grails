package phone.rest

import phone.rest.command.ListCommand

class PhoneController {

    def index(ListCommand listCmd) {
        def json = [:]
        if(listCmd.hasErrors()) {
            response.status = 401
            json.status = "error"
            json.errors = []
            listCmd.errors.fieldErrors.each {
                log.debug("Rejected value ${it.rejectedValue} for field ${it.field}")
                json.errors << "Rejected value ${it.rejectedValue} for field ${it.field}"
            }
        }
        else {
            try {
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
                json.status = "ok"
            }
            catch(Exception e) {
                response.status = 500
                json.status = "error"
                json.errors = ["${e.class}: ${e.message}"]
                log.error("Unexpected error thrown in index() action of PhoneController, incoming params were ${params}", e)
            }
        }
        render(contentType: 'application/json') {
            json
        }
    }

    def show(Integer id) {

    }
}
