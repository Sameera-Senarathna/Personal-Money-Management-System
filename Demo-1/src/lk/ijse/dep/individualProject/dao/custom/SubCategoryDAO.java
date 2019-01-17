package lk.ijse.dep.individualProject.dao.custom;

import lk.ijse.dep.individualProject.dao.CrudDAO;
import lk.ijse.dep.individualProject.entity.SubCategory;

import java.util.List;
import java.util.Optional;

public interface SubCategoryDAO extends CrudDAO<SubCategory,Integer> {

    Optional<List<SubCategory>> getSubCategoryByCategoryId(int categoryID) throws Exception;

}
