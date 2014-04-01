package phone.rest.marshaller

import org.codehaus.groovy.grails.web.converters.Converter
import org.codehaus.groovy.grails.web.converters.configuration.DefaultConverterConfiguration
import org.codehaus.groovy.grails.web.converters.marshaller.ClosureObjectMarshaller
import org.codehaus.groovy.grails.web.converters.marshaller.ObjectMarshaller

/**
 * Created by chris on 3/28/14.
 */
class NamedMarshaller {

    ObjectMarshaller marshaller
    Class<? extends Converter> converterClass
    int priority = DefaultConverterConfiguration.DEFAULT_PRIORITY;
    String name
    Closure closure
    Class clazz

    public NamedMarshaller(Class<? extends Converter> converterClass, Class clazz, Closure closure) {
        this.converterClass = converterClass
        this.clazz = clazz
        this.closure = closure
        this.marshaller = new ClosureObjectMarshaller(clazz, closure)
    }
}
