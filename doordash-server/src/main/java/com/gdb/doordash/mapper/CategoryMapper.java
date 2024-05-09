package com.gdb.doordash.mapper;

import com.gdb.doordash.dto.CategoryPageQueryDTO;
import com.gdb.doordash.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 分页查询
     */
    List<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 新增分类
     */
    @Insert("insert into category (type, name, sort, status, create_time, update_time, create_user, update_user) "
            + "values "
            + "(#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    int insertCategory(Category category);

    /**
     * 根据id修改分类
     */
    int updateCategory(Category category);

    /**
     * 根据id删除分类
     */
    @Delete("delete from category where id = #{id}")
    int deleteCategory(Long id);
}
