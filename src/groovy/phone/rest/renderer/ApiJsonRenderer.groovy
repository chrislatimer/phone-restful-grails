package phone.rest.renderer

import com.google.gson.Gson
import com.google.gson.stream.JsonWriter
import grails.converters.JSON
import grails.rest.render.AbstractRenderer
import grails.rest.render.ContainerRenderer
import grails.rest.render.RenderContext
import grails.util.GrailsWebUtil
import org.codehaus.groovy.grails.web.json.JSONWriter
import org.codehaus.groovy.grails.web.mime.MimeType
import phone.rest.converters.ApiJSON

/**
 * Created by chris on 4/5/14.
 */
class ApiJsonRenderer<T> extends AbstractRenderer<T> {

    String label

    public ApiJsonRenderer(Class<T> targetClass) {
        super(targetClass, MimeType.JSON);
    }

    @Override
    void render(T object, RenderContext context) {
        context.setContentType(GrailsWebUtil.getContentType(MimeType.JSON.name, GrailsWebUtil.DEFAULT_ENCODING))
        ApiJSON converter
        def detail = context.arguments?.detail ?: "compact"
        def out = context.writer
        JSONWriter writer = new JSONWriter(out)

        JSON.use(detail) {
            converter = object as ApiJSON
            writer.object()
            writer.key(getLabel())
            converter.renderPartial(writer)
        }



        if(context.arguments?.meta) {
            writer.key("meta")
            converter = context.arguments.meta as ApiJSON
            converter.renderPartial(writer)
        }

        if(context.arguments?.include) {
            writer.key("include")
            writer.array()
            context.arguments?.include.each { includeProp ->
                JSON.use("compact") {
                    converter = object.properties.get(includeProp) as ApiJSON
                }


                writer.object()
                writer.key(includeProp)
                converter.renderPartial(writer)
                writer.endObject()

            }
            writer.endArray()
        }

        writer.endObject()

        out.flush()
        out.close()
    }

    String getLabel() {
        if(label) {
            label
        }
        else if(this instanceof ContainerRenderer) {
            "entities"
        }
        else {
            "entity"
        }
    }
}
