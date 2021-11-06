package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService service;

    @Mock
    BindingResult result;

    @InjectMocks
    OwnerController controller;

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner(1L, "John", "Wright");
    }

    @Test
    void testProcessCreationFormWhenBindingResultHasErrors() {
        // given
        when(result.hasErrors()).thenReturn(true);

        // when
        String form = controller.processCreationForm(owner, result);

        // then
        assertEquals("owners/createOrUpdateOwnerForm", form);
        verify(result, times(1)).hasErrors();
    }


    @Test
    void testProcessCreationFormWhenBindingResultHasNoErrors() {
        // given
        when(result.hasErrors()).thenReturn(false);

        Owner savedOwner = new Owner(5L, "HKU", "Captain");

        when(service.save(argThat(argument ->
                argument.getFirstName().equals("John")
                        && argument.getLastName().equals("Wright")
        ))).thenReturn(savedOwner);

        // when
        String form = controller.processCreationForm(owner, result);

        // then
        assertEquals("redirect:/owners/5", form);
        verify(result, times(1)).hasErrors();
        verify(service, times(1)).save(owner);
    }

}