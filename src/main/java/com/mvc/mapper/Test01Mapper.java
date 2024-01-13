package com.mvc.mapper;

import com.mvc.model.Test01;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Test01Mapper {

    List<Test01> getCityList();
}
