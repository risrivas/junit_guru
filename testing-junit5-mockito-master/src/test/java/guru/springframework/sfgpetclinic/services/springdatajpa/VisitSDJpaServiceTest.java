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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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
        // when
        service.deleteById(1L);

        // then
        then(visitRepository).should(times(1)).deleteById(1L);
        // verify(visitRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete() {
        // given
        Visit visit = new Visit(1L);

        // when
        service.delete(visit);

        // then
        then(visitRepository).should().delete(visit);
        // verify(visitRepository).delete(visit);
    }

    @Test
    void testSave() {
        // given
        Visit visit = new Visit(1L);
        visit.setDescription("mock test");

        given(visitRepository.save(visit)).willReturn(visit);
        // when(visitRepository.save(visit)).thenReturn(visit);

        // when
        final Visit save = service.save(visit);

        // then
        then(visitRepository).should().save(visit);
        // verify(visitRepository).save(visit);
        assertEquals("mock test", save.getDescription());
    }

    @Test
    void testFindById() {
        // given
        Visit visit = new Visit(1L);
        visit.setDescription("mock test");

        given(visitRepository.findById(1L)).willReturn(Optional.of(visit));
        // when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));

        // when
        final Visit save = service.findById(1L);

        // then
        then(visitRepository).should().findById(1L);
        // verify(visitRepository).findById(1L);

        assertNotNull(visit);
        assertEquals("mock test", save.getDescription());
    }

    @Test
    void testFindAll() {
        // given
        Set<Visit> visits = Arrays.stream(new Visit[]
                        {
                                new Visit(1L),
                                new Visit(2L),
                                new Visit(3L)
                        })
                .collect(Collectors.toSet());

        given(visitRepository.findAll()).willReturn(visits);
        // when(visitRepository.findAll()).thenReturn(visits);

        // when
        final Set<Visit> allVisits = service.findAll();

        // then
        then(visitRepository).should().findAll();
        // verify(visitRepository).findAll();

        assertEquals(visits, allVisits);
    }
}