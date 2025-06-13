package runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // folder tempat file .feature kamu
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepDefinitions") // package step definitions-mu

@ConfigurationParameter(
        key = FEATURES_PROPERTY_NAME,
        value = "classpath:features/delete_product.feature"
) //Ini kalau hanya ingin menjalankan satu features saja

public class TestRunner {
}
