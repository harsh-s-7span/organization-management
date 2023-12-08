package com.organization.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Metadata {
    private int pageNumber;
    private int pageSize;
    private long totalRecords;
    private int totalPages;
}
