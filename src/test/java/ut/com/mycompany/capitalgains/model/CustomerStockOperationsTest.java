package ut.com.mycompany.capitalgains.model;

import com.mycompany.capitalgains.model.CustomerStockOperations;
import com.mycompany.capitalgains.model.Operations;
import com.mycompany.capitalgains.model.Tax;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mycompany.capitalgains.model.Operation.buyOperationWith;
import static com.mycompany.capitalgains.model.Operation.sellOperationWith;
import static org.junit.jupiter.api.Assertions.*;

class CustomerStockOperationsTest {

    @Test
    public void whenCustomerIsSellingMoreThanItHasThenThrowException() {
        Operations operations = new Operations();
        operations.add(buyOperationWith(10, 100));
        operations.add(sellOperationWith(10, 101));
        CustomerStockOperations customerStockOperations = CustomerStockOperations.of(operations);

        assertThrows(IllegalArgumentException.class, () -> customerStockOperations.calculateTaxes().taxes());
    }

    @Test
    public void whenCustomerIsSellingMoreThanItHasCaseTwoThenThrowException() {
        Operations operations = new Operations();
        operations.add(buyOperationWith(10, 100));
        operations.add(sellOperationWith(10, 98));
        operations.add(buyOperationWith(10, 10));
        operations.add(sellOperationWith(10, 13));
        CustomerStockOperations customerStockOperations = CustomerStockOperations.of(operations);

        assertThrows(IllegalArgumentException.class, () -> customerStockOperations.calculateTaxes().taxes());
    }

    @Test
    public void whenCustomerIsSellingAllThenSuccess() {
        Operations operations = new Operations();
        operations.add(buyOperationWith(10, 100));
        operations.add(sellOperationWith(10, 50));
        operations.add(sellOperationWith(10, 50));
        CustomerStockOperations customerStockOperations = CustomerStockOperations.of(operations);

        assertDoesNotThrow(() -> customerStockOperations.calculateTaxes().taxes());
    }

    @Test
    public void testCase1() {
        Operations operations = new Operations();
        operations.add(buyOperationWith(10, 100));
        operations.add(sellOperationWith(15, 50));
        operations.add(sellOperationWith(15, 50));
        CustomerStockOperations customerStockOperations = CustomerStockOperations.of(operations);
        List<Tax> taxes = customerStockOperations.calculateTaxes().taxes();
        assertEquals(3, taxes.size());
        assertEquals(0, taxes.get(0).value());
        assertEquals(0, taxes.get(1).value());
        assertEquals(0, taxes.get(2).value());
    }

    @Test
    public void testCase2() {
        Operations operations = new Operations();
        operations.add(buyOperationWith(10, 10_000));
        operations.add(sellOperationWith(20, 5_000));
        operations.add(sellOperationWith(5, 5_000));
        CustomerStockOperations customerStockOperations = CustomerStockOperations.of(operations);
        List<Tax> taxes = customerStockOperations.calculateTaxes().taxes();
        assertEquals(3, taxes.size());
        assertEquals(0, taxes.get(0).value());
        assertEquals(10_000, taxes.get(1).value());
        assertEquals(0, taxes.get(2).value());
    }

    @Test
    public void testCase3() {
        Operations operations = new Operations();
        operations.add(buyOperationWith(10, 10000));
        operations.add(sellOperationWith(5, 5000));
        operations.add(sellOperationWith(20, 3000));
        CustomerStockOperations customerStockOperations = CustomerStockOperations.of(operations);
        List<Tax> taxes = customerStockOperations.calculateTaxes().taxes();
        assertEquals(3, taxes.size());
        assertEquals(0, taxes.get(0).value());
        assertEquals(0, taxes.get(1).value());
        assertEquals(1_000, taxes.get(2).value());
    }

    @Test
    public void testCase4() {
        Operations operations = new Operations();
        operations.add(buyOperationWith(10, 10_000));
        operations.add(buyOperationWith(25, 5_000));
        operations.add(sellOperationWith(15, 10_000));
        CustomerStockOperations customerStockOperations = CustomerStockOperations.of(operations);
        List<Tax> taxes = customerStockOperations.calculateTaxes().taxes();
        assertEquals(3, taxes.size());
        assertEquals(0, taxes.get(0).value());
        assertEquals(0, taxes.get(1).value());
        assertEquals(0, taxes.get(2).value());
    }

    @Test
    public void testCase5() {
        Operations operations = new Operations();
        operations.add(buyOperationWith(10, 10_000));
        operations.add(buyOperationWith(25, 5_000));
        operations.add(sellOperationWith(15, 10_000));
        operations.add(sellOperationWith(25, 5_000));
        CustomerStockOperations customerStockOperations = CustomerStockOperations.of(operations);
        List<Tax> taxes = customerStockOperations.calculateTaxes().taxes();
        assertEquals(4, taxes.size());
        assertEquals(0, taxes.get(0).value());
        assertEquals(0, taxes.get(1).value());
        assertEquals(0, taxes.get(2).value());
        assertEquals(10_000, taxes.get(3).value());
    }

    @Test
    public void testCase6() {
        Operations operations = new Operations();
        operations.add(buyOperationWith(10, 10_000));
        operations.add(sellOperationWith(2, 5_000));
        operations.add(sellOperationWith(20, 2_000));
        operations.add(sellOperationWith(20, 2_000));
        operations.add(sellOperationWith(25, 1_000));
        CustomerStockOperations customerStockOperations = CustomerStockOperations.of(operations);
        List<Tax> taxes = customerStockOperations.calculateTaxes().taxes();
        assertEquals(5, taxes.size());
        assertEquals(0, taxes.get(0).value());
        assertEquals(0, taxes.get(1).value());
        assertEquals(0, taxes.get(2).value());
        assertEquals(0, taxes.get(3).value());
        assertEquals(3_000, taxes.get(4).value());
    }

    @Test
    public void testCase7() {
        Operations operations = new Operations();
        operations.add(buyOperationWith(10, 10_000));
        operations.add(sellOperationWith(2, 5_000));
        operations.add(sellOperationWith(20, 2_000));
        operations.add(sellOperationWith(20, 2_000));
        operations.add(sellOperationWith(25, 1_000));
        operations.add(buyOperationWith(20, 10_000));
        operations.add(sellOperationWith(15, 5_000));
        operations.add(sellOperationWith(30, 4_350));
        operations.add(sellOperationWith(30, 650));
        CustomerStockOperations customerStockOperations = CustomerStockOperations.of(operations);
        List<Tax> taxes = customerStockOperations.calculateTaxes().taxes();
        assertEquals(9, taxes.size());
        assertEquals(0, taxes.get(0).value());
        assertEquals(0, taxes.get(1).value());
        assertEquals(0, taxes.get(2).value());
        assertEquals(0, taxes.get(3).value());
        assertEquals(3_000, taxes.get(4).value());
        assertEquals(0, taxes.get(5).value());
        assertEquals(0, taxes.get(6).value());
        assertEquals(3_700, taxes.get(7).value());
        assertEquals(0, taxes.get(8).value());
    }

    @Test
    public void testCase8() {
        Operations operations = new Operations();
        operations.add(buyOperationWith(10, 10_000));
        operations.add(sellOperationWith(50, 10_000));
        operations.add(buyOperationWith(20, 10_000));
        operations.add(sellOperationWith(50, 10_000));
        CustomerStockOperations customerStockOperations = CustomerStockOperations.of(operations);

        List<Tax> taxes = customerStockOperations.calculateTaxes().taxes();
        assertEquals(4, taxes.size());
        assertEquals(0, taxes.get(0).value());
        assertEquals(80_000, taxes.get(1).value());
        assertEquals(0, taxes.get(2).value());
        assertEquals(60_000, taxes.get(3).value());
    }

}