package lk.ijse.dep.individualProject.business.custom;

import lk.ijse.dep.individualProject.business.SuperBO;
import lk.ijse.dep.individualProject.dto.CategoryDTO;
import lk.ijse.dep.individualProject.dto.SubCategoryDTO;
import lk.ijse.dep.individualProject.dto.SubCategoryTableDTO;
import lk.ijse.dep.individualProject.entity.SubCategory;

import java.util.List;

public interface SubCategoryBO extends SuperBO {

    boolean addSubCategory(SubCategoryDTO subCategoryDTO) throws Exception;

    List<SubCategoryTableDTO> getAllSubCategory() throws Exception;

    boolean updateCategory(SubCategoryDTO subCategoryDTO) throws Exception;

    boolean deletesubCategory(int key) throws Exception;

    List<SubCategoryDTO> getSubCategoryByCategoryID(int CategoryID) throws Exception;

}
