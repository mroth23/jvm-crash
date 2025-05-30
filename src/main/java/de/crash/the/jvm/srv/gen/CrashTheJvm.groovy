package de.crash.the.jvm.srv.gen

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j2
import org.apache.commons.configuration2.PropertiesConfiguration
import org.apache.commons.configuration2.convert.DisabledListDelimiterHandler
import org.apache.commons.lang3.StringUtils
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine

import java.util.*

@Log4j2
@groovy.transform.InheritConstructors
class CrashTheJvm {

    public static void main(String[] args)
    {
        new CrashTheJvm().execute()
    }

    protected void execute() {
        Properties properties = new Properties()
        properties.put('resource.loader.file.cache', true)
        properties.put('resource.loader.classpath.class', 'org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader')
        properties.put('resource.loaders', 'classpath')
        properties.put('webapp-slf4j-logger.level', 'debug')
        VelocityEngine engine = new VelocityEngine(properties)

        Map<String, String> description = new HashMap<>()
        description.put("test", "test")
        var columnDefinition = ColumnDefinition.mock(description)

        var cols = new ArrayList<ColumnDefinition>()

        for (int i = 0; i < 200; i++) {
            cols.add(columnDefinition)
        }

        for (int i = 0; i < 2000; i++) {
            VelocityContext context = new VelocityContext()
            context.put('cols', cols)
            def writer = new StringWriter()
            def template = engine.getTemplate('etyif.java')
            template.merge(context, writer)
        }
    }

    static class ColumnDefinition {
        Map<String, String> description
        String m_comment

        static mock(Map<String, String> description) {
            ColumnDefinition cd = new ColumnDefinition()
            cd.description = description
            cd.m_comment = 'Test1234'
            cd
        }

        String description(String a_language) {
            description[a_language] ?: m_comment
        }

        String description() {
            description(_Lib.getProp("lang", Locale.GERMAN.getLanguage()))
        }
    }

    static class _Lib {
        static String getProp(String a_name, String a_default = null,
                              boolean a_ignoreCase = false) {
            _Configuration.get().getValue(a_name, a_default)
        }

        static String env(String a_name) {
            return System.getenv(a_name)
        }

        static String getFoo() {
            return env("UMGEBUNG")
        }

        static String getBar() {
            return env("STANDORT")
        }

        static String getBaz() {
            return StringUtils.defaultString(env("SYSTEMIDENTIFIER"))
        }
    }

    static class _Configuration {
        static Map<_ConfScope, _Configuration> m_configurations = new HashMap<>();
        private _ConfScope m_scope
        private PropertiesConfiguration m_configuration

        private _Configuration(_ConfScope a_scope, PropertiesConfiguration a_configuration) {
            m_scope = a_scope
            m_configuration = a_configuration
        }

        public String getValue(String a_key, String a_default = null) {
            def value = m_configuration.getString(a_key, a_default)
            value = System.getProperty(a_key, value)
            value
        }

        static get(_ConfScope a_scope = null) {
            _ConfScope scope = _ConfScope.fromEnvironment()
            if (!m_configurations.containsKey(scope)) {
                m_configurations.put(scope, new _Configuration(scope, read(scope)))
            }
            m_configurations.get(scope)
        }

        static final PropertiesConfiguration emptyConfiguration(_ConfScope a_scope) {
            PropertiesConfiguration configuration = new PropertiesConfiguration()
            configuration.setListDelimiterHandler(new DisabledListDelimiterHandler())
            configuration
        }

        static PropertiesConfiguration read(_ConfScope a_scope) {
            PropertiesConfiguration configuration = emptyConfiguration(a_scope)
            for (int i = 0; i < 555; i++) {
                configuration.addProperty("prop_" + i, i)
            }
            configuration
        }
    }

    @EqualsAndHashCode(includeFields = true)
    static class _ConfScope {
        private static final int SIZE = 3
        private final String[] m_components = new String[SIZE]
        public static final String WILDCARD = '*'
        private static final int INDEX_FOO = 0
        private static final int INDEX_BAR = 1
        private static final int INDEX_BAZ = 2

        _ConfScope(String a_foo = WILDCARD, String a_bar = WILDCARD, String a_baz = WILDCARD) {
            m_components[INDEX_FOO] = StringUtils.isEmpty(a_foo) ? _Lib.getFoo() : a_foo
            m_components[INDEX_BAR] = StringUtils.isEmpty(a_bar) ? _Lib.getBar() : a_bar
            m_components[INDEX_BAZ] = StringUtils.isEmpty(a_baz) ? _Lib.getBaz() : a_baz
        }

        static fromEnvironment() {
            new _ConfScope(_Lib.getFoo(), _Lib.getBar(), _Lib.getBaz())
        }
    }
}
