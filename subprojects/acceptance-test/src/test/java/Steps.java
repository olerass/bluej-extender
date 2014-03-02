import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.netbeans.jemmy.operators.*;

import static bluejFrame;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Steps {
    private JDialogOperator extensionsDialog;
    private JDialogOperator extensionDetailsDialog;

    @Given("^the extension is placed in the extensions folder$")
    public void the_extension_is_placed_in_the_correct_folder() throws Throwable {
        // Already done by Gradle build
    }

    @When("^I open the installed extensions window$")
    public void I_open_the_installed_extensions_window() throws Throwable {
        new JMenuBarOperator(bluejFrame()).pushMenuNoBlock("Help|Installed Extensions");
        extensionsDialog = new JDialogOperator(bluejFrame(), "BlueJ:  Installed Extensions");
    }

    @Then("^I should see the extension listed$")
    public void I_should_see_the_extension_listed() throws Throwable {
        JTableOperator tbl = new JTableOperator(extensionsDialog);
        tbl.waitCell("loaded", 0, 1);
        tbl.waitCell("exampleName", 0, 2);
        tbl.waitCell("System", 0, 3);
    }

    @When("^I open the extension details window$")
    public void I_open_the_extension_details_window() throws Throwable {
        JTableOperator tbl = new JTableOperator(extensionsDialog);
        tbl.clickOnCell(0, 0);
        extensionDetailsDialog = new JDialogOperator(bluejFrame(), "Extension Details");
    }

    @Then("^I should see the extensions name$")
    public void I_should_see_the_extensions_name() throws Throwable {
        new JLabelOperator(extensionDetailsDialog).waitText("exampleName version 1.0.0");

    }

    @Then("^I should see the extensions description$")
    public void I_should_see_the_extensions_description() throws Throwable {
        new JTextAreaOperator(extensionDetailsDialog).waitText("An example extension built using the BlueJ Extender system.");
    }

    @Then("^I should see the extensions website$")
    public void I_should_see_the_extensions_website() throws Throwable {
        String txt = new JLabelOperator(extensionDetailsDialog, 4).getText();
        assertThat(txt, is("http://github.com/olerass/bluej-extender"));
    }
}