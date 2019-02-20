package com.zym.gaea.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author master
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Command {
    private String dir;
    private String groupId;
    private String artifactId;
}
