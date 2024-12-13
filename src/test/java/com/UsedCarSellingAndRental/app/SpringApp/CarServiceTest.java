package com.UsedCarSellingAndRental.app.SpringApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.UsedCarSellingAndRental.app.SpringApp.exception.ResourceNotFoundException;
import com.UsedCarSellingAndRental.app.SpringApp.model.Car;
import com.UsedCarSellingAndRental.app.SpringApp.repository.CarRepository;
import com.UsedCarSellingAndRental.app.SpringApp.service.CarService;

@SpringBootTest
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    private Car testCar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testCar = new Car(1, "Tesla Model S", "2022", null, null, "Sedan", 60000, "New", "Single", "10000", "150", null);
    }

    @Test
    void testValidateCarValidId() throws ResourceNotFoundException {
        Mockito.when(carRepository.findById(1)).thenReturn(Optional.of(testCar));
        
        Car result = carService.validate(1);
        assertNotNull(result);
        assertEquals(testCar.getId(), result.getId());
    }

    @Test
    void testUpdateCar() {
        Car updatedCar = new Car(1, "Tesla Model X", "2023", null, null, "SUV", 70000, "New", "Single", "5000", "200", null);
        Mockito.when(carRepository.save(updatedCar)).thenReturn(updatedCar);

        Car result = carService.updateCar(updatedCar);
        assertNotNull(result);
        assertEquals(updatedCar.getCar_model(), result.getCar_model());
    }
    
}


