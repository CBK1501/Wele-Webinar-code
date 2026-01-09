package com.wele.learning.model;

import com.wele.learning.enums.SpeakerRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeakerWebinarMapping {

    private ObjectId webinarId;
    private SpeakerRole role;

    public ObjectId getWebinarId() {
        return webinarId;
    }

    public void setWebinarId(ObjectId webinarId) {
        this.webinarId = webinarId;
    }

    public SpeakerRole getRole() {
        return role;
    }

    public void setRole(SpeakerRole role) {
        this.role = role;
    }
}

