package com.goatstickers.DTO.Product;

import java.util.List;

public class SectionDTO {
    public String title;
    public List<ProductSummaryDTO> products;
    public SectionDTO(String title, List<ProductSummaryDTO> products){}
}

