package phone.rest.converters

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONWriter

/**
 * Created by chris on 4/13/14.
 */
class ApiJSON extends JSON {
    private final static Log log = LogFactory.getLog(ApiJSON.class);

    public ApiJSON() {
        super()
    }

    public ApiJSON(Object target) {
        super(target)
    }

    public void renderPartial(JSONWriter out) {
        initWriter(out)
        super.value(target)
    }

    protected initWriter(JSONWriter out) {
        writer = out
        referenceStack = new Stack<Object>();
    }
}
