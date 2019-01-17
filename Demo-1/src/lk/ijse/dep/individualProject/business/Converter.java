package lk.ijse.dep.individualProject.business;

import lk.ijse.dep.individualProject.dto.*;
import lk.ijse.dep.individualProject.entity.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Converter {

    public static  <T extends SuperDTO> T getDTO(SuperEntity superEntity){
        if(superEntity instanceof Category){
            Category c = (Category) superEntity;
            return (T) new CategoryDTO(c.getcId(),c.getcName(),c.getcDescription(),c.getcType());
        }else if(superEntity instanceof SubCategory){
            SubCategory sc = (SubCategory) superEntity;
            return (T) new SubCategoryDTO(sc.getScId(),sc.getScName(),sc.getScDescription(),sc.getcId());
        }else if(superEntity instanceof Account){
            Account a = (Account) superEntity;
            return (T) new AccountDTO(a.getaId(),a.getaName(),a.getaDescription());
        }else if(superEntity instanceof Transaction){
            Transaction t = (Transaction) superEntity;
            return (T) new TransactionDTO(t.gettId(),t.gettDate(),t.gettNote(),t.gettValue(),t.getaId(),t.getScId());
        }else {
            throw new RuntimeException("This Entity Can not Convert to DTO");
        }
    }

    public static <T extends SuperEntity> T getEntity(SuperDTO superDTO){
        if(superDTO instanceof CategoryDTO){
            CategoryDTO c = (CategoryDTO) superDTO;
            return (T) new Category(c.getcId(),c.getcName(),c.getcDescription(),c.getcType());
        }else if(superDTO instanceof SubCategoryDTO){
            SubCategoryDTO s = (SubCategoryDTO) superDTO;
            return (T) new SubCategory(s.getScId(),s.getScName(),s.getScDescription(),s.getcId());
        }else if(superDTO instanceof AccountDTO){
            AccountDTO a = (AccountDTO) superDTO;
            return (T) new Account(a.getAId(),a.getAccountName(),a.getAccountDescription());
        }else if(superDTO instanceof TransactionDTO){
            TransactionDTO t = (TransactionDTO) superDTO;
            return (T) new Transaction(t.gettId(),t.getDate(),t.getNote(),t.getValue(),t.getAccountId(),t.getScId());
        }else {
            throw new RuntimeException("This DTO can not converted to an Entity");
        }
    }

    public static <T extends SuperDTO> List<T> getDTOList(List<? extends SuperEntity> entities){
        return entities.stream().map(new Function<SuperEntity, T>() {
            @Override
            public T apply(SuperEntity superEntity) {
                return getDTO(superEntity);
            }
        }).collect(Collectors.toList());
    }

    public static <T extends SuperEntity> List<T> getEntityList(List<? extends SuperDTO> dtos){
        return dtos.stream().map(new Function<SuperDTO, T>() {
            @Override
            public T apply(SuperDTO superDTO) {
                return getEntity(superDTO);
            }
        }).collect(Collectors.toList());
    }

    //-------------------- For Custom Query DTO ----------------------------//

    public static <T extends SuperDTO> T getDTO(CustomerEntity entity,Class<T> dtoClass){
        if(dtoClass == SubCategoryTableDTO.class){
            return (T) new SubCategoryTableDTO(entity.getScId(),entity.getScName(),entity.getScDescription(),entity.getcName(),entity.getcType());
        }else if(dtoClass == TransactionViewDTO.class){
            return (T) new TransactionViewDTO(entity.gettId(),entity.gettDate(),entity.gettNote(),entity.getTvalue(),entity.getaId(),
                    entity.getScId(),entity.getcId(),entity.getcType(),entity.getcName(),entity.getScName());
        }else if(dtoClass == AccountTableDTO.class){
            return (T) new AccountTableDTO(entity.getaId(),entity.getaName(),entity.getaDescription(),entity.getBalance());
        }else if(dtoClass == TableReportDTO.class){
            return (T) new TableReportDTO(entity.getcId(),entity.getcName(),entity.getCategotyTotal());
        }else if(dtoClass==TableReportDrillDTO.class){
            return (T) new TableReportDrillDTO(entity.getScName(),entity.getSubCategoryTotal());
        }else {
             throw new RuntimeException("This DTO can not Convert");
        }
    }

    public static <T extends SuperDTO> List<T> getDTOList(List<CustomerEntity> list , Class<T> dtoClass){
        return list.stream().map(new Function<CustomerEntity, T>() {
            @Override
            public T apply(CustomerEntity entity) {
                return getDTO(entity,dtoClass);
            }
        }).collect(Collectors.toList());
    }

}
