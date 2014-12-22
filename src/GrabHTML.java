import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GrabHTML {
	public static String readHTML(String website) throws Exception {
		URL siteURL = new URL(website);
		URLConnection siteSpoof = siteURL.openConnection();

		// spoof user-agent to imitate browser
		siteSpoof
				.setRequestProperty(
						"User-Agent",
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");

		BufferedReader siteIn = new BufferedReader(new InputStreamReader(
				siteSpoof.getInputStream()));
		String siteHTML = "";
		String siteLine;
		while ((siteLine = siteIn.readLine()) != null) {
			siteHTML += siteLine;
		}
		return siteHTML;
	}
}
