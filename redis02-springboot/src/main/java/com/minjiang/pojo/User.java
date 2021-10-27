package com.minjiang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @auther guannw
 * @create 2021/7/8 16:43
 */

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {

    private String name;
    private int age;
}
