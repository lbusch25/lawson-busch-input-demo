package com.lawsonbusch.inputdemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskProperties<F extends TaskField> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1115187246981180834L;

    private List<F> fields;
}
