package com.productManagement.helper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.productManagement.dto.OfferDetailsDTO;
import com.productManagement.dto.ProductDetailsDTO;
import com.productManagement.dto.ProductOfferDTO;
import com.productManagement.dto.StockDetailsDTO;
import com.productManagement.entity.OfferDetails;
import com.productManagement.entity.ProductDetails;
import com.productManagement.entity.ProductOffer;
import com.productManagement.entity.StockDetails;

@Component
public class Mapper {

    @Autowired
    private ModelMapper modelMapper;

    // ProductDetails Mappings
    public ProductDetails productDtoToEntity(ProductDetailsDTO productDetailsDTO) {
        return modelMapper.map(productDetailsDTO, ProductDetails.class);
    }

    public ProductDetailsDTO productEntityToDto(ProductDetails productDetails) {
        ProductDetailsDTO productDetailsDTO = modelMapper.map(productDetails, ProductDetailsDTO.class);
        // Include product offers with full details
        if (productDetails.getProductOffers() != null) {
            List<ProductOfferDTO> productOfferDTOs = productDetails.getProductOffers().stream()
                .map(this::productOfferEntityToDto) // Mapping each ProductOffer to DTO
                .toList();
            productDetailsDTO.setProductOffers(productOfferDTOs);
        }
        return productDetailsDTO;
    }

    // ProductOffer Mappings
    public ProductOffer productOfferDtoToEntity(ProductOfferDTO productOfferDTO) {
        ProductOffer productOffer = modelMapper.map(productOfferDTO, ProductOffer.class);
        return productOffer;
    }

    public ProductOfferDTO productOfferEntityToDto(ProductOffer productOffer) {
        ProductOfferDTO productOfferDTO = modelMapper.map(productOffer, ProductOfferDTO.class);
        
        // Set the relationship manually for nested OfferDetails
        if (productOffer.getOffer() != null) {
            OfferDetailsDTO offerDetailsDTO = modelMapper.map(productOffer.getOffer(), OfferDetailsDTO.class);
            productOfferDTO.setOfferDetails(offerDetailsDTO); // Include complete OfferDetails
        }

        return productOfferDTO;
    }

    // OfferDetails Mappings
    public OfferDetails offerDetailsDtoToEntity(OfferDetailsDTO offerDetailsDTO) {
        return modelMapper.map(offerDetailsDTO, OfferDetails.class);
    }

    public OfferDetailsDTO offerDetailsEntityToDto(OfferDetails offerDetails) {
        return modelMapper.map(offerDetails, OfferDetailsDTO.class);
    }

    // StockDetails Mappings
    public StockDetails stockDetailsDtoToEntity(StockDetailsDTO stockDetailsDTO) {
        StockDetails stockDetails = modelMapper.map(stockDetailsDTO, StockDetails.class);
        if (stockDetailsDTO.getProducts() != null) {
            stockDetails.setProducts(modelMapper.map(stockDetailsDTO.getProducts(), 
                new org.modelmapper.TypeToken<List<ProductDetails>>() {}.getType()));
        }
        return stockDetails;
    } 

    public StockDetailsDTO stockDetailsEntityToDto(StockDetails stockDetails) {
        StockDetailsDTO stockDetailsDTO = modelMapper.map(stockDetails, StockDetailsDTO.class);
        if (stockDetails.getProducts() != null) {
            stockDetailsDTO.setProducts(modelMapper.map(stockDetails.getProducts(), 
                new org.modelmapper.TypeToken<List<ProductDetailsDTO>>() {}.getType()));
        }
        return stockDetailsDTO;
    }
}
