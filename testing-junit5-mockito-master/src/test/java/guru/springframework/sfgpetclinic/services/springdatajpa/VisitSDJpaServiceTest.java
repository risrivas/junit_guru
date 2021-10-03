package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by rishi on 03/10/2021
 */
@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitSDJpaService service;

    @Test
    void testDeleteById() {
        service.deleteById(1L);
        verify(visitRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete() {
        Visit visit = new Visit(1L);
        service.delete(visit);
        verify(visitRepository).delete(visit);
    }

    @Test
    void testSave() {
        Visit visit = new Visit(1L);
        visit.setDescription("mock test");

        when(visitRepository.save(visit)).thenReturn(visit);

        final Visit save = service.save(visit);
        verify(visitRepository).save(visit);
        assertEquals("mock test", save.getDescription());
    }

    @Test
    void testFindById() {
        Visit visit = new Visit(1L);
        visit.setDescription("mock test");

        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));

        final Visit save = service.findById(1L);
        verify(visitRepository).findById(1L);

        assertNotNull(visit);
        assertEquals("mock test", save.getDescription());
    }

    @Test
    void testFindAll() {
        Set<Visit> visits = Arrays.stream(new Visit[]
                        {
                                new Visit(1L),
                                new Visit(2L),
                                new Visit(3L)
                        })
                .collect(Collectors.toSet());

        when(visitRepository.findAll()).thenReturn(visits);

        final Set<Visit> allVisits = service.findAll();
        verify(visitRepository).findAll();
        
        assertEquals(visits, allVisits);
    }
}