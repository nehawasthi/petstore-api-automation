package base;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.JsonUtils;

public class BaseTest {

    // Shared API context for all test classes
    protected static APIRequestContext request;

    // Playwright instance for creating and managing API sessions
    protected static Playwright playwright;

    // Base URL is dynamically loaded from config.json based on environment key
    protected static final String BASE_URL = JsonUtils.fetchUrl("Urls.qa").asText();
    // Optional: switch to dev
    // protected static final String BASE_URL = JsonUtils.fetchUrl("Urls.dev").asText();
    // Optional: switch to prod
    // protected static final String BASE_URL = JsonUtils.fetchUrl("Urls.prod").asText();


    /**
     * This method runs once before any test class executes.
     * It initializes the Playwright engine and configures the API request context.
     */
    @BeforeSuite
    public void setup() {
        // Start Playwright
        playwright = Playwright.create();

        // Create a new API request context with base URL and default headers
        request = playwright.request().newContext(
            new APIRequest.NewContextOptions()
                .setBaseURL(BASE_URL)
                .setExtraHTTPHeaders(java.util.Map.of("Accept", "application/json"))
        );
    }

    /**
     * This method runs once after all test classes have executed.
     * It closes and cleans up the Playwright session and API context.
     */
    @AfterSuite
    public void tearDown() {
        // Dispose the API context after tests
        if (request != null) {
            request.dispose();
        }

        // Close Playwright instance
        if (playwright != null) {
            playwright.close();
        }
    }
}