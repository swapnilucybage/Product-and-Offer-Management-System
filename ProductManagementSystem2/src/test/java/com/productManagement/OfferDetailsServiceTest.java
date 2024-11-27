package com.productManagement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.productManagement.dto.OfferDetailsDTO;
import com.productManagement.entity.OfferDetails;
import com.productManagement.enums.OfferStatus;
import com.productManagement.exception.ResourceNotFoundException;
import com.productManagement.helper.Mapper;
import com.productManagement.repository.OfferDetailsRepository;
import com.productManagement.service.OfferDetailsService;

@SpringBootTest
@ContextConfiguration(classes = { ProductManagementSystem2Application.class })
class OfferDetailsServiceTest {

    @Mock
    private OfferDetailsRepository offerDetailsRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private OfferDetailsService offerDetailsService;

    public OfferDetailsServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveOrUpdateOffer() {
        OfferDetailsDTO offerDto = new OfferDetailsDTO();
        offerDto.setActivationDate(LocalDate.now().minusDays(1));
        offerDto.setExpiryDate(LocalDate.now().plusDays(10));
        offerDto.setStatus(OfferStatus.PENDING);

        OfferDetails offerEntity = new OfferDetails();
        offerEntity.setActivationDate(LocalDate.now().minusDays(1));
        offerEntity.setExpiryDate(LocalDate.now().plusDays(10));
        offerEntity.setStatus(OfferStatus.ACTIVE);

        when(mapper.offerDetailsDtoToEntity(offerDto)).thenReturn(offerEntity);
        when(offerDetailsRepository.save(any(OfferDetails.class))).thenReturn(offerEntity);
        when(mapper.offerDetailsEntityToDto(offerEntity)).thenReturn(offerDto);

        OfferDetailsDTO result = offerDetailsService.saveOrUpdateOffer(offerDto);

        assertNotNull(result);
        assertEquals(OfferStatus.ACTIVE, result.getStatus());
        verify(offerDetailsRepository, times(1)).save(any(OfferDetails.class));
    }

    @Test
    public void testGetOfferById() {
        Long offerId = 1L;
        OfferDetails offerEntity = new OfferDetails();
        offerEntity.setId(offerId);
        offerEntity.setCode("OFFER1");

        OfferDetailsDTO offerDto = new OfferDetailsDTO();
        offerDto.setId(offerId);
        offerDto.setCode("OFFER1");

        when(offerDetailsRepository.findById(offerId)).thenReturn(Optional.of(offerEntity));
        when(mapper.offerDetailsEntityToDto(offerEntity)).thenReturn(offerDto);

        OfferDetailsDTO result = offerDetailsService.getOfferById(offerId);

        assertNotNull(result);
        assertEquals(offerId, result.getId());
        verify(offerDetailsRepository, times(1)).findById(offerId);
    }

    @Test
    public void testGetOfferById_NotFound() {
        Long offerId = 1L;
        when(offerDetailsRepository.findById(offerId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> offerDetailsService.getOfferById(offerId));
        verify(offerDetailsRepository, times(1)).findById(offerId);
    }

    @Test
    public void testDeleteOffer() {
        Long offerId = 1L;
        when(offerDetailsRepository.existsById(offerId)).thenReturn(true);

        offerDetailsService.deleteOffer(offerId);

        verify(offerDetailsRepository, times(1)).deleteById(offerId);
    }

    @Test
    public void testDeleteOffer_NotFound() {
        Long offerId = 1L;
        when(offerDetailsRepository.existsById(offerId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> offerDetailsService.deleteOffer(offerId));
        verify(offerDetailsRepository, times(1)).existsById(offerId);
    }

    @Test
    public void testGetAllOffers() {
        List<OfferDetails> offerEntities = new ArrayList<>();
        OfferDetails offer1 = new OfferDetails();
        offer1.setId(1L);
        offer1.setCode("OFFER1");
        offerEntities.add(offer1);

        OfferDetailsDTO offerDto1 = new OfferDetailsDTO();
        offerDto1.setId(1L);
        offerDto1.setCode("OFFER1");

        when(offerDetailsRepository.findAll()).thenReturn(offerEntities);
        when(mapper.offerDetailsEntityToDto(offer1)).thenReturn(offerDto1);

        List<OfferDetailsDTO> result = offerDetailsService.getAllOffers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(offerDetailsRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateOfferStatuses() {
        LocalDate today = LocalDate.now();

        OfferDetails pendingOffer = new OfferDetails();
        pendingOffer.setId(1L);
        pendingOffer.setStatus(OfferStatus.PENDING);
        pendingOffer.setActivationDate(today.minusDays(1));
        pendingOffer.setExpiryDate(today.plusDays(10));

        List<OfferDetails> pendingOffers = new ArrayList<>();
        pendingOffers.add(pendingOffer);

        when(offerDetailsRepository.findByStatusAndActivationDateBefore(OfferStatus.PENDING, today)).thenReturn(pendingOffers);

        offerDetailsService.updateOfferStatuses();

        assertEquals(OfferStatus.ACTIVE, pendingOffer.getStatus());
        verify(offerDetailsRepository, times(1)).save(pendingOffer);
    }
}
