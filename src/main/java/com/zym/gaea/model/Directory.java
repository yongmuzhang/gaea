package com.zym.gaea.model;

import com.zym.gaea.enumeration.DirectoryTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author master
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Directory {
    private String name;
    private DirectoryTypeEnum type;
    private List<Directory> directoryList;
}
