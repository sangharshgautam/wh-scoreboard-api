package com.sangharsh.oms.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class ResultDTO<U> {
    private long total;
    private List<U> records = new ArrayList<>();
}
