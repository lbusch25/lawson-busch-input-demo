package com.lawsonbusch.inputdemo.dgs;

import com.lawsonbusch.inputdemo.domain.ConcreteTaskPropertiesWrapper;
import com.lawsonbusch.inputdemo.domain.FieldType;
import com.lawsonbusch.inputdemo.domain.TaskConfigurationField;
import com.lawsonbusch.inputdemo.domain.TaskProperties;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import java.util.List;
import javax.validation.constraints.NotNull;

@DgsComponent
public class GenericDataFetcher {

    /**
     * A query demonstrating that the return type works as expected.
     *
     * @return - A Parameterized TaskProperties
     */
    @DgsQuery
    public TaskProperties<TaskConfigurationField<?>> getFakeGeneric() {
        return TaskProperties.<TaskConfigurationField<?>>builder()
                .fields(List.of(TaskConfigurationField.builder()
                                .fieldName("test field")
                                .type(FieldType.LONG)
                                .value(1L)
                                .build(),
                        TaskConfigurationField.builder()
                                .fieldName("test field 2")
                                .type(FieldType.BOOLEAN)
                                .value(true)
                                .build()))
                .build();
    }

    /**
     * This example demonstrates how the DGS input serialization struggles with wrapped generic types. It can't find
     * type F - which is the generic type used in the class.
     *
     * @param taskProperties - wrapper class for general generic
     * @return - should return the input object, hits class not found F exception
     */
    @DgsMutation
    public TaskProperties<TaskConfigurationField<?>> testGenericFieldTypeNotFound(@NotNull @InputArgument
    TaskProperties<TaskConfigurationField<?>> taskProperties) {
        return taskProperties;
    }

    /**
     * This example demonstrates how using a concrete typed wrapper also breaks the serialization. DGS's
     * DefaultInputObjectMapper's getFieldType method fails to convert the fields: [TaskField] to a
     * List<TaskConfiguration<?>> - expecting a single TaskConfiguration.
     *
     * @param wrapper - a wrapper object with a concrete TaskProperties implementation
     * @return - the wrapper object
     */
    @DgsMutation
    public ConcreteTaskPropertiesWrapper testGenericFieldListNotWrappedWhenConcreteInput(
            @NotNull @InputArgument ConcreteTaskPropertiesWrapper wrapper) {
        return wrapper;
    }


    /**
     * Demonstrates that a work around exists where we need to unwrap the object and just pass the list. This becomes a
     * nuisance when working with complex objects and validation on inputs.
     *
     * @param taskConfigurationFields - the unwrapped list input
     * @return - the passed in input fields
     */
    @DgsMutation
    public List<TaskConfigurationField<?>> testGenericWorkAround(
            @NotNull @InputArgument List<TaskConfigurationField<?>> taskConfigurationFields) {
        return taskConfigurationFields;
    }
}
