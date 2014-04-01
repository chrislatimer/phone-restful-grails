package phone.rest

import grails.converters.JSON
import grails.converters.XML
import grails.rest.RestfulController

class PhoneController extends RestfulController {
    static responseFormats = ['json', 'xml']

    public PhoneController() {
        super(Phone)
    }

    def index() {
        params.max = Math.min(max ?: 10, 100)
        respond Phone.list(params), model:[phoneCount: Phone.count()]
    }

    def show(Phone phone) {
        withFormat {
            json {
                JSON.use(params?.detail?.toLowerCase() ?: "complete") {
                    respond phone
                }
            }
            xml {
                XML.use(params?.detail?.toLowerCase() ?: "complete") {
                    respond phone
                }
            }
        }
    }
}
