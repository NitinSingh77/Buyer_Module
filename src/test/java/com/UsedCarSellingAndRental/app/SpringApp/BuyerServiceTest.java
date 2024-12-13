package com.UsedCarSellingAndRental.app.SpringApp;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.UsedCarSellingAndRental.app.SpringApp.enums.Role;
import com.UsedCarSellingAndRental.app.SpringApp.model.Buyer;
import com.UsedCarSellingAndRental.app.SpringApp.model.User;
import com.UsedCarSellingAndRental.app.SpringApp.repository.BuyerRepository;
import com.UsedCarSellingAndRental.app.SpringApp.repository.UserRepository;
import com.UsedCarSellingAndRental.app.SpringApp.service.BuyerService;
import com.UsedCarSellingAndRental.app.SpringApp.service.UserService;

@SpringBootTest
public class BuyerServiceTest {

    @InjectMocks
    private BuyerService buyerService;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BuyerRepository buyerRepository;

    private User user;
    private Buyer buyer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User(1, "NitinSingh12", "password123", "Nitin Singh", Role.BUYER, true);
        buyer = new Buyer();
        buyer.setId(1);
        buyer.setEmail("nitin@gmail.com");
        buyer.setPhone("9876543210");
        buyer.setAddress("Dehradun 12");
        buyer.setUser(user);
    }

    @Test
    public void testGetAllBuyer() {
        List<Buyer> buyers = Arrays.asList(buyer);
        when(buyerRepository.findAll()).thenReturn(buyers);

        List<Buyer> result = buyerService.getAllBuyer();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("nitin@gmail.com", result.get(0).getEmail());
        verify(buyerRepository, times(1)).findAll();
    }

    @Test
    public void testSaveBuyer() {
        when(buyerRepository.save(buyer)).thenReturn(buyer);

        buyerService.saveBuyer(buyer);

        verify(buyerRepository, times(1)).save(buyer);
    }

    @Test
    public void testDeleteBuyerById() {
        when(buyerRepository.findById(1)).thenReturn(Optional.of(buyer));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        buyerService.deleteBuyerById(1);

        verify(buyerRepository, times(1)).deleteById(1);
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    public void testGetBuyerDetailsByUsername() {
        when(buyerRepository.getBuyerDetailsByUsername("NitinSingh12")).thenReturn(buyer);

        Buyer result = buyerService.getBuyerDetailsByUsername("NitinSingh12");

        assertNotNull(result);
        assertEquals("nitin@gmail.com", result.getEmail());
        verify(buyerRepository, times(1)).getBuyerDetailsByUsername("NitinSingh12");
    }

    @Test
    public void testInsertBuyer() {
        when(buyerRepository.save(buyer)).thenReturn(buyer);

        Buyer result = buyerService.insert(buyer);

        assertNotNull(result);
        assertEquals("nitin@gmail.com", result.getEmail());
        verify(buyerRepository, times(1)).save(buyer);
    }
}
