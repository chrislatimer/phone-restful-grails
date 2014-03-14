package phone.rest.command

import grails.validation.Validateable

@Validateable
class ListCommand {
    Integer max
    Integer offset

    static constraints = {
        max nullable: true, min: 0
        offset nullable: true, min: 0
    }
}
