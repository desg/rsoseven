import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class dickbutts {

	@Test
	public void test() {
		String a = "<td id=190191>1929292929</td>";
		Pattern codeBaseRex = Pattern.compile("^<.+>([0-9]+)</.+>[ ]*$");
		Matcher m1 = codeBaseRex.matcher(a);
		/*
		 * while (m1.find()){ System.out.println("found:");
		 * System.out.println(m1.group()); }
		 */
		if (m1.find()) {
			System.out.println("match: " + m1.group(1));
		}

	}

}