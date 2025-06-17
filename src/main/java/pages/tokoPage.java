package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;

public class tokoPage {
    WebDriver driver;
    WebDriverWait wait;

    public tokoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Wait for the edit form by checking visibility of a specific input field
    public void waitForEditForm() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='nama_warung']")));
            System.out.println("✓ Edit form is loaded");
        } catch (Exception e) {
            System.out.println("✗ Edit form not loaded within timeout");
            debugPageElements("Edit Form");
            throw new RuntimeException("Edit form not found after clicking Ubah");
        }
    }

    public WebElement getStoreNameField() {
        String[] selectors = {
                "//input[@name='nama_warung']", // Primary selector
                "//input[@id='nama_warung']",
                "//input[@placeholder='Nama Warung']"
        };
        return findElementWithMultipleSelectors(selectors, "Store Name Field");
    }

    public WebElement getStoreAddressField() {
        String[] selectors = {
                "//input[@name='alamat_warung']", // Primary selector
                "//input[@id='alamat_warung']",
                "//textarea[@name='alamat_warung']"
        };
        WebElement element = findElementWithMultipleSelectors(selectors, "Store Address Field");
        System.out.println("Address field required attribute: " + element.getAttribute("required"));
        return element;
    }

    public WebElement getDescriptionField() {
        String[] selectors = {
                "//input[@name='deskripsi']", // Primary selector
                "//textarea[@name='deskripsi']",
                "//input[@id='deskripsi']"
        };
        return findElementWithMultipleSelectors(selectors, "Description Field");
    }

    public WebElement getPhoneNumberField() {
        String[] selectors = {
                "//input[@name='nomor_hp']", // Primary selector
                "//input[@id='nomor_hp']",
                "//input[@type='tel']"
        };
        return findElementWithMultipleSelectors(selectors, "Phone Number Field");
    }

    public WebElement getVillageField() {
        String[] selectors = {
                "//input[@name='kelurahan']", // Primary selector
                "//select[@name='kelurahan']",
                "//input[@id='kelurahan']"
        };
        return findElementWithMultipleSelectors(selectors, "Village Field");
    }

    public WebElement getEditButton() {
        String[] selectors = {
                "//a[contains(text(), 'Ubah')]", // Primary selector from output
                "//button[contains(text(), 'Ubah')]",
                "//a[contains(text(), 'Edit')]"
        };
        return findElementWithMultipleSelectors(selectors, "Edit Button");
    }

    public WebElement getSaveButton() {
        String[] selectors = {
                "//button[text()='Simpan']", // Primary selector
                "//button[contains(text(), 'Simpan')]",
                "//button[@type='submit']"
        };
        return findElementWithMultipleSelectors(selectors, "Save Button");
    }

    public WebElement getCancelButton() {
        String[] selectors = {
                "//button[text()='Batal']",
                "//button[contains(text(), 'Batal')]"
        };
        return findElementWithMultipleSelectors(selectors, "Cancel Button");
    }

    public WebElement getErrorMessage(String expectedMessage) {
        String[] selectors = {
                "//*[contains(text(), '" + expectedMessage + "')]",
                "//div[contains(@class, 'error') or contains(@class, 'alert')]"
        };
        return findElementWithMultipleSelectors(selectors, "Error Message");
    }

    private WebElement findElementWithMultipleSelectors(String[] selectors, String elementName) {
        for (String selector : selectors) {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector)));
                System.out.println("✓ Found " + elementName + " with selector: " + selector);
                return element;
            } catch (Exception e) {
                System.out.println("✗ Failed to find " + elementName + " with selector: " + selector);
            }
        }
        debugPageElements(elementName);
        throw new RuntimeException("Could not find " + elementName + " with any of the provided selectors");
    }

    private void debugPageElements(String elementName) {
        System.out.println("=== DEBUG: Looking for " + elementName + " ===");
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("=== All form elements ===");
        List<WebElement> formElements = driver.findElements(By.xpath("//input | //textarea | //select"));
        for (WebElement element : formElements) {
            System.out.println("Element: " + element.getTagName() +
                    " | name: '" + element.getAttribute("name") +
                    "' | id: '" + element.getAttribute("id") + "'");
        }
    }

    public void fillStoreForm(String name, String address, String desc, String phone, String village) {
        System.out.println("=== Filling store form ===");
        WebElement nameField = getStoreNameField();
        nameField.clear();
        nameField.sendKeys(name);
        WebElement addressField = getStoreAddressField();
        addressField.clear();
        if (address != null && !address.isEmpty()) {
            addressField.sendKeys(address);
        }
        WebElement descField = getDescriptionField();
        descField.clear();
        descField.sendKeys(desc);
        WebElement phoneField = getPhoneNumberField();
        phoneField.clear();
        phoneField.sendKeys(phone);
        WebElement villageField = getVillageField();
        if (villageField.getTagName().equals("select")) {
            villageField.findElement(By.xpath(".//option[text()='" + village + "']")).click();
        } else {
            villageField.clear();
            villageField.sendKeys(village);
        }
        System.out.println("✓ Successfully filled all form fields");
    }

    // Use JavaScript click for reliability
    public void clickEditButton() {
        WebElement editButton = getEditButton();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", editButton);
    }

    public void clickSaveButton() {
        WebElement saveButton = getSaveButton();
        saveButton.click();
    }
}