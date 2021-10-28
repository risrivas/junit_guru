package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

/**
 * Created by rishi on 01/10/2021
 */
@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void testDeleteByObject() {
        // given
        Speciality speciality = new Speciality();

        // when
        service.delete(speciality);

        // then
        then(specialtyRepository).should().delete(speciality);
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void findByIdTest() {
        // given
        Speciality speciality = new Speciality("bdd");
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        // when
        Speciality foundSpeciality = service.findById(1L);

        // then
        assertThat(foundSpeciality).isNotNull();
        assertThat(foundSpeciality.getDescription()).isEqualTo("bdd");
        then(specialtyRepository).should().findById(1L);
        then(specialtyRepository).should(times(1)).findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteById() {
        // given - none

        // when
        service.deleteById(1L);
        service.deleteById(1L);

        // then
        then(specialtyRepository).should(times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        // given - none

        // when
        service.deleteById(1L);
        service.deleteById(1L);

        // then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        // given - none

        // when
        service.deleteById(1L);
        service.deleteById(1L);

        // then
        then(specialtyRepository).should(atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        // given - none

        // when
        service.deleteById(1L);
        service.deleteById(1L);

        // then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
        then(specialtyRepository).should(never()).deleteById(4L);
    }

    @Test
    void testDelete() {
        // when
        service.delete(new Speciality());

        // then
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any(Speciality.class));
        assertThrows(RuntimeException.class,
                () -> specialtyRepository.delete(new Speciality()));
        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void testFindByIDThrows() {
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("boom"));
        assertThrows(RuntimeException.class,
                () -> specialtyRepository.findById(1L));
        then(specialtyRepository).should().findById(1L);
    }

    @Test
    void testDeleteBDD() {
        willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any(Speciality.class));
        assertThrows(RuntimeException.class,
                () -> specialtyRepository.delete(new Speciality()));
        then(specialtyRepository).should().delete(any(Speciality.class));
    }
}