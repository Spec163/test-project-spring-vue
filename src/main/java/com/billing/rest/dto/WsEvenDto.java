package com.billing.rest.dto;

import com.billing.rest.domain.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonRawValue;

@Data
@AllArgsConstructor
@JsonView(Views.Id.class)
public class WsEvenDto {
    private ObjectType objectType;
    private EventType eventType;

    @JsonRawValue
    private String body;
}
