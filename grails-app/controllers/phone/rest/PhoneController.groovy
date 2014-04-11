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
        params.max = Math.min(params.max ?: 10, 100)
        withFormat {
            json {
                respond Phone.list(params), [detail:"complete", model:[phoneCount: Phone.count()]]
            }
            xml {
                XML.use(params?.detail?.toLowerCase() ?: "complete") {
                    respond phone
                }
            }
        }
    }

    def show(Phone phone) {
        def detail = params.detail ?: "complete"
        withFormat {
            json {
                respond(phone, [detail:detail])
            }
            xml {
                XML.use(params?.detail?.toLowerCase() ?: "complete") {
                    respond phone
                }
            }
        }
    }
}
