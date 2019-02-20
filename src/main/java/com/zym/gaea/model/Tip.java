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
public class Tip {
    private String key;
    private String message;
}
