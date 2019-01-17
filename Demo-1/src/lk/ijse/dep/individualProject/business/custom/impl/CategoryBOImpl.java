package lk.ijse.dep.individualProject.business.custom.impl;

import lk.ijse.dep.individualProject.business.Converter;
import lk.ijse.dep.individualProject.business.custom.CategoryBO;
import lk.ijse.dep.individualProject.dao.DAOFactory;
import lk.ijse.dep.individualProject.dao.custom.CategoryDAO;
import lk.ijse.dep.individualProject.dto.CategoryDTO;
import lk.ijse.dep.individualProject.entity.Category;

import java.util.List;
import java.util.function.Function;

public class CategoryBOImpl implements CategoryBO {

    CategoryDAO categoryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CATEGORY);

    @Override
    public boolean addCategory(CategoryDTO categoryDTO) throws Exception {
        return categoryDAO.save(Converter.getEntity(categoryDTO));
    }

    @Override
    public List<CategoryDTO> getAllCategory() throws Exception {
        return categoryDAO.findAll().map(new Function<List<Category>, List<CategoryDTO>>() {
            @Override
            public List<CategoryDTO> apply(List<Category> categories) {
                return Converter.getDTOList(categories);
            }
        }).get();
    }

    @Override
    public boolean updateCategory(CategoryDTO categoryDTO) throws Exception {
        return categoryDAO.update(Converter.getEntity(categoryDTO));
    }

    @Override
    public boolean deleteCategory(int cId) throws Exception {
        return categoryDAO.delete(cId);
    }
}
