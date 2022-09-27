package com.lawsonbusch.inputdemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskConfigurationField<T> implements TaskField {

    @NotBlank
    @Getter(AccessLevel.NONE)
    private String fieldName;
    @NotNull
    @Getter(AccessLevel.NONE)
    private FieldType type;
    private T value;

    public String getFieldName() {
        return fieldName;
    }

    @NotNull
    public FieldType getType() {
        return type;
    }
}
