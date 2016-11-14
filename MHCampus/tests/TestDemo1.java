import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDemo1
	{

		@BeforeClass
		public static void setUpBeforeClass() throws Exception
			{
			}

		@AfterClass
		public static void tearDownAfterClass() throws Exception
			{
			}

		@Before
		public void setUp() throws Exception
			{
			}

		@After
		public void tearDown() throws Exception
			{
			}

		@Test
		public void testDemo()
			{
				int x = 7;
				x += 2;
				
				assertEquals("Check that arithmetics in works in JAVA!", 9, x);
			}

	}
