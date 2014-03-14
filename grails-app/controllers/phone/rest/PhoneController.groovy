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
        render(contentType: 'application/json') {
            json
        }
    }
}
