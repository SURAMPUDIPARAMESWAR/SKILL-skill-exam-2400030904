package com.klef.soa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import com.klef.soa.entity.WaterBill;
import com.klef.soa.repository.WaterBillRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class WaterBillRepositoryTest {

	@Autowired
	private WaterBillRepository repository;
	
	@Test
    void testFindAllBills() 
    {
        List<WaterBill> bills = repository.findAll();
        assertNotNull(bills);
        assertFalse(bills.isEmpty());
        System.out.println("Total Users: " + bills.size());
    }
	
	@Test
    void testFindBillById() 
    {
        Optional<WaterBill> bill = repository.findById(2L);
        assertTrue(bill.isPresent());
        assertEquals(2L, bill.get().getWaterBillId());
        
    }
	
	@Test
    void testFindUserByName()
    {
        List<WaterBill> bills = repository.findByHolderName("Ram");

        assertNotNull(bills);
        assertFalse(bills.isEmpty());
        assertTrue(bills.stream()
                .allMatch(bill -> "Ram".equals(bill.getHolderName())));
    }
	
	@Test
    void testTotalBillsCount() 
    {
        long count = repository.count();
        assertEquals(2L, count);
    }
	
	@Test
    void testBillDetails() 
    {
        Optional<WaterBill> user = repository.findById(2L);
        assertTrue(user.isPresent());
        WaterBill foundUser = user.get();
        assertNotNull(foundUser.getHolderName());
        assertNotNull(foundUser.getLocation());
        assertNotNull(foundUser.getStatus());
    }

}
