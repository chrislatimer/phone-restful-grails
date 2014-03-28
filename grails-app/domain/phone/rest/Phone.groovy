package phone.rest

import grails.rest.Resource

@Resource(uri='/phones', readOnly=true)
class Phone extends Product {

    static constraints = {
    }
}
