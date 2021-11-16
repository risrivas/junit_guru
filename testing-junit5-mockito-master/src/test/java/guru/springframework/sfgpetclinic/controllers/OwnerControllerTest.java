package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService service;

    @InjectMocks
    OwnerController controller;

    @Mock
    BindingResult result;

    @Mock
    Model model;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner(1L, "John", "Wright");

        when(service.findAllByLastNameLike(stringArgumentCaptor.capture()))
                .thenAnswer(invocation -> {
                    List<Owner> owners = new ArrayList<>();
                    String name = invocation.getArgument(0);
                    if (name.equals("%Wright%")) {
                        owners.add(owner);
                        return owners;
                    } else if (name.equals("%DontFindMe%")) {
                        return owners;
                    } else if (name.equals("%FindMe%")) {
                        owners.add(owner);
                        owners.add(new Owner(2L, "John2", "Wright2"));
                        return owners;
                    }

                    throw new RuntimeException("Invalid Argument");
                });
    }

    @Test
    void testProcessFindFormWildcardStringAnnotation() {
        // given
        // List<Owner> owners = new ArrayList<>();
        // when(service.findAllByLastNameLike(stringArgumentCaptor.capture())).thenReturn(owners);

        // when
        String viewName = controller.processFindForm(owner, result, null);

        // then
        assertEquals("%Wright%", stringArgumentCaptor.getValue());
        assertEquals("redirect:/owners/1", viewName);
    }

    @Test
    void testProcessFindFormWildcardNotFound() {
        // given
        owner = new Owner(1L, "John", "DontFindMe");

        // when
        String viewName = controller.processFindForm(owner, result, null);

        // then
        assertEquals("%DontFindMe%", stringArgumentCaptor.getValue());
        assertEquals("owners/findOwners", viewName);
        verifyZeroInteractions(model);
    }

    @Test
    void testProcessFindFormWildcardFound() {
        // given
        owner = new Owner(1L, "John", "FindMe");
        InOrder inOrder = inOrder(service, model);

        // when
        String viewName = controller.processFindForm(owner, result, model);

        // then
        assertEquals("%FindMe%", stringArgumentCaptor.getValue());
        assertEquals("owners/ownersList", viewName);

        // inorder asserts
        inOrder.verify(service).findAllByLastNameLike(anyString());
        inOrder.verify(model, times(1)).addAttribute(anyString(), anyList());
        verifyNoMoreInteractions(model);
    }

    @Test
    void testProcessFindFormWildcardString() {
        // given
        List<Owner> owners = new ArrayList<>();
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        when(service.findAllByLastNameLike(captor.capture())).thenReturn(owners);

        // when
        String viewName = controller.processFindForm(owner, result, null);

        // then
        assertEquals("%Wright%", captor.getValue());
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