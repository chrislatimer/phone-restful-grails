package phone.rest.renderer

import grails.converters.JSON
import grails.rest.render.AbstractRenderer
import grails.rest.render.RenderContext
import grails.util.GrailsWebUtil
import org.codehaus.groovy.grails.web.mime.MimeType

/**
 * Created by chris on 4/5/14.
 */
class ApiJsonRenderer<T> extends AbstractRenderer<T> {

    public ApiJsonRenderer(Class<T> targetClass) {
        super(targetClass, MimeType.JSON);
    }

    @Override
    void render(T object, RenderContext context) {
        context.setContentType(GrailsWebUtil.getContentType(MimeType.JSON.name, GrailsWebUtil.DEFAULT_ENCODING))
        JSON converter
        def detail = context.arguments?.detail ?: "compact"

        JSON.use(detail) {
            converter = object as JSON
        }

        converter.render(context.getWriter())
    }
}
