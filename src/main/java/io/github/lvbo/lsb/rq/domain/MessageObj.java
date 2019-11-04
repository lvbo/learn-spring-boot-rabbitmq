package io.github.lvbo.lsb.rq.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class MessageObj implements Serializable {
    private boolean ack;
    private int id;
    private String name;
    private String value;
}
