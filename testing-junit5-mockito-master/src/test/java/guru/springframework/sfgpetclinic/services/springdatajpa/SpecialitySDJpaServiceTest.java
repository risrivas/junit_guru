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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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
        Speciality speciality = new Speciality();
        service.delete(speciality);
        verify(specialtyRepository).delete(speciality);
        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void findByIdTest() {
        Speciality speciality = new Speciality("mock special");
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        Speciality foundSpeciality = service.findById(1L);

        assertThat(foundSpeciality).isNotNull();
        assertThat(foundSpeciality.getDescription()).isEqualTo("mock special");

        verify(specialtyRepository).findById(1L);
        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void findByIdBddTest() {
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
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
        verify(specialtyRepository, never()).deleteById(4L);
    }

    @Test
    void testDelete() {
        service.delete(new Speciality());
    }

}