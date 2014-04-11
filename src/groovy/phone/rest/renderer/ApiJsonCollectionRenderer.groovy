package phone.rest.renderer

import grails.rest.render.ContainerRenderer
import grails.util.GrailsNameUtils
import org.codehaus.groovy.grails.web.mime.MimeType

/**
 * Created by chris on 4/9/14.
 */
class ApiJsonCollectionRenderer extends ApiJsonRenderer implements ContainerRenderer {
    final Class componentType

    public ApiJsonCollectionRenderer(Class componentType) {
        super(Collection)
        this.componentType = componentType
    }

    public ApiJsonCollectionRenderer(Class componentType, MimeType... mimeTypes) {
        super(Collection, mimeTypes)
        this.componentType = componentType
    }
}
