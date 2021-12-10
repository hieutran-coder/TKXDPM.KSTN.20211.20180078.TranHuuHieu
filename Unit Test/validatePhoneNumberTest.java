package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class validatePhoneNumberTest {
	
	private PlaceOrderController  placeOrderController;
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"0123456789,true",
		"111111111111111,false",
		"01234567890, flase" ,
		"1234567 890, false", 
		"123, false",
		"a123456789, false",
		"@ 12345678, falise"
	})
		
	@Test
	void testValidatePhoneNumber() {
		boolean isValid = placeOrderController.validatePhoneNumber("01234567890");
		assertEquals(true, isValid);
		
	}

}
