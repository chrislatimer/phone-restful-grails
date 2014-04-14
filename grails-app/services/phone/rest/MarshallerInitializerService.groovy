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

    public void initialize() {
        registerNamedJsonMarshallers()
        registerNamedXmlMarshallers()
    }

    protected registerNamedJsonMarshallers() {
        def configs = [:]
        for (Object o : applicationContext.getBeansOfType(NamedMarshallerJson.class).values()) {
            def c = ConvertersConfigurationHolder.getConverterConfiguration(JSON.class)
            NamedMarshallerJson namedMarshaller = (NamedMarshallerJson) o
            if(!configs[namedMarshaller.name]) {
                configs[namedMarshaller.name] = new DefaultConverterConfiguration<JSON>(c)
            }
            DefaultConverterConfiguration<JSON> cfg = configs[namedMarshaller.name]
            cfg.registerObjectMarshaller(namedMarshaller.marshaller)
            ConvertersConfigurationHolder.setNamedConverterConfiguration(JSON.class, namedMarshaller.name, cfg);
        }
    }

    protected registerNamedXmlMarshallers() {
        def configs = [:]
        for (Object o : applicationContext.getBeansOfType(NamedMarshallerXml.class).values()) {
            def c = ConvertersConfigurationHolder.getConverterConfiguration(XML.class)
            NamedMarshallerXml namedMarshaller = (NamedMarshallerXml) o
            if(!configs[namedMarshaller.name]) {
                configs[namedMarshaller.name] = new DefaultConverterConfiguration<XML>(c)
            }
            DefaultConverterConfiguration<XML> cfg = configs[namedMarshaller.name]
            cfg.registerObjectMarshaller(namedMarshaller.marshaller)
            ConvertersConfigurationHolder.setNamedConverterConfiguration(XML.class, namedMarshaller.name, cfg)
        }
    }
}
