package lk.ijse.dep.individualProject.business.custom;

import lk.ijse.dep.individualProject.business.SuperBO;
import lk.ijse.dep.individualProject.dto.CategoryDTO;

import java.util.List;

public interface CategoryBO extends SuperBO{

    boolean addCategory(CategoryDTO categoryDTO) throws Exception;

    List<CategoryDTO> getAllCategory() throws Exception;

    boolean updateCategory(CategoryDTO categoryDTO) throws Exception;

    boolean deleteCategory(int cId) throws Exception;

}
