package com.lawsonbusch.inputdemo.domain;

import java.io.Serializable;

public interface TaskField extends Serializable {
    FieldType getType();
    String getFieldName();
}
