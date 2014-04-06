package phone.rest

import grails.converters.JSON
import grails.converters.XML
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.converters.configuration.ConvertersConfigurationHolder
import org.codehaus.groovy.grails.web.converters.configuration.DefaultConverterConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import phone.rest.marshaller.NamedMarshallerJson
import phone.rest.marshaller.NamedMarshallerXml


@Transactional
class MarshallerInitializerService implements ApplicationContextAware {

    ApplicationContext applicationContext

    protected registerNamedJsonMarshallers() {
        for (Object o : applicationContext.getBeansOfType(NamedMarshallerJson.class).values()) {
            def c = ConvertersConfigurationHolder.getConverterConfiguration(JSON.class)
            DefaultConverterConfiguration<JSON> cfg = new DefaultConverterConfiguration<JSON>(c)
            NamedMarshallerJson namedMarshaller = (NamedMarshallerJson) o
            cfg.registerObjectMarshaller(namedMarshaller.marshaller)
            ConvertersConfigurationHolder.setNamedConverterConfiguration(JSON.class, namedMarshaller.name, cfg);
        }
    }

    protected registerNamedXmlMarshallers() {
        for (Object o : applicationContext.getBeansOfType(NamedMarshallerXml.class).values()) {
            def c = ConvertersConfigurationHolder.getConverterConfiguration(XML.class)
            DefaultConverterConfiguration<XML> cfg = new DefaultConverterConfiguration<XML>(c)
            NamedMarshallerXml namedMarshaller = (NamedMarshallerXml) o
            cfg.registerObjectMarshaller(namedMarshaller.marshaller)
            ConvertersConfigurationHolder.setNamedConverterConfiguration(XML.class, namedMarshaller.name, cfg)
        }
    }
}
