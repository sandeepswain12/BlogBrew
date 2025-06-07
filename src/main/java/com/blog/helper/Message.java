package com.blog.helper;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Message {
    private String content;
    @Builder.Default
    private MessageType type = MessageType.blue;
}
