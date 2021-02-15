package ru.appline.tests;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm",
                "progress",
                "summary"},
        glue = {"ru.appline.steps"},
        features = {"src/test/resources/"},
       tags = "@test"
)
public class CucumberRunnerTest {
}



