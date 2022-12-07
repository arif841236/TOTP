package com.indusnet.model.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum Type {

	SMS("sms"),
	EMAIL("email");
	
	private String typeVariable;
    private Type(String type) {
        this.typeVariable=type;
    }
}
