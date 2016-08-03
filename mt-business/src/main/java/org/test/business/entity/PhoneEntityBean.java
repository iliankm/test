package org.test.business.entity;

import javax.validation.constraints.NotNull;
import org.mongodb.morphia.annotations.Embedded;
import org.test.business.api.entity.PhoneEntity;
import org.test.business.api.entity.util.PhoneType;

@Embedded
public class PhoneEntityBean implements PhoneEntity {

    private static final long serialVersionUID = 4329488264002771374L;

    @NotNull(message = "validation_phone_type_is_mandatory")
    private PhoneType type;

    @NotNull(message = "validation_phone_is_mandatory")
    private String phone;

    /**
     * Default no-args constructor needed by persistence framework
     */
    @SuppressWarnings("unused")
    private PhoneEntityBean() {}

    public PhoneEntityBean(PhoneType type, String phone) {

        this.type = type;
        this.phone = phone;
    }

    @Override
    public PhoneType getType() {

	return type;
    }

    @Override
    public String getPhone() {

	return phone;
    }
}
