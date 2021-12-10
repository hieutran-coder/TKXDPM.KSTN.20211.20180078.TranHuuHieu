package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class validateNameTest {
	private PlaceOrderController placeOrderController;
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}
	@ParameterizedTest
	@CsvSource({
		"1TranHuuHieu, false",
		"@HieuTran Blabl, false",
		" , false",
		"Hieu Tran Huu, false",
		"TranHuuHieu, false"
	})
	
	@Test
	void test() {
		boolean isValided = placeOrderController.validateName("TranHuuHieu");
		assertEquals(true, isValided);
		
	}

}
