package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    @Spy
    PetMapService petMapService;

    @InjectMocks
    VisitController visitController;

    @Test
    void loadPetWithVisitMock() {
        // given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(1L);
        given(petService.findById(anyLong())).willReturn(pet);

        // when
        Visit visit = visitController.loadPetWithVisit(1L, model);

        // then
        assertNotNull(visit);
        assertNotNull(visit.getPet());
    }

    @Test
    void loadPetWithVisitSpy() {
        // given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(12L);
        Pet pet1 = new Pet(3L);
        petMapService.save(pet);
        petMapService.save(pet1);

        given(petMapService.findById(anyLong())).willCallRealMethod();

        // when
        Visit visit = visitController.loadPetWithVisit(12L, model);

        // then
        assertNotNull(visit);
        assertNotNull(visit.getPet());
        assertEquals(12L, visit.getPet().getId(), 0.001);
        verify(petMapService, times(1)).findById(anyLong());
    }
}