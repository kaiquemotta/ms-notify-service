package com.video.notify;

import io.cucumber.junit.platform.engine.Cucumber;
import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.*;

@Suite
@Cucumber
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.video.notify.bdd")
public class CucumberTestRunner {
}