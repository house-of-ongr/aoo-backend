package com.aoo.admin.adapter.out.persistence.mapper;

import com.aoo.admin.application.port.in.category.CategoryInfo;
import com.aoo.admin.application.port.in.category.CreateCategoryResult;
import com.aoo.admin.application.port.in.category.SearchCategoryResult;
import com.aoo.admin.application.port.in.category.UpdateCategoryResult;
import com.aoo.admin.domain.universe.UniverseCategory;
import com.aoo.common.adapter.out.persistence.entity.CategoryJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public CreateCategoryResult mapToCreateCategoryResult(CategoryJpaEntity newCategory) {
        return new CreateCategoryResult(
                String.format("[#%d]번 카테고리가 생성되었습니다.", newCategory.getId()),
                newCategory.getId(),
                newCategory.getTitleKor(),
                newCategory.getTitleEng()
        );
    }

    public SearchCategoryResult mapToSearchCategoryResult(List<CategoryJpaEntity> categoryJpaEntities) {
        return new SearchCategoryResult(categoryJpaEntities.stream()
                .map(categoryJpaEntity -> new CategoryInfo(
                        categoryJpaEntity.getId(),
                        categoryJpaEntity.getTitleEng(),
                        categoryJpaEntity.getTitleKor()
                ))
                .toList());
    }

    public UpdateCategoryResult mapToUpdateCategoryResult(CategoryJpaEntity updatedCategory) {
        return new UpdateCategoryResult(
                String.format("[#%d]번 카테고리가 수정되었습니다.", updatedCategory.getId()),
                updatedCategory.getId(),
                updatedCategory.getTitleEng(),
                updatedCategory.getTitleKor()
        );
    }

    public UniverseCategory mapToUniverseCategory(CategoryJpaEntity category) {
        return new UniverseCategory(
                category.getId(),
                category.getTitleEng(),
                category.getTitleKor()
        );
    }

    public CategoryInfo mapToCategoryInfo(CategoryJpaEntity category) {
        return new CategoryInfo(
                category.getId(),
                category.getTitleEng(),
                category.getTitleKor()
        );
    }
}
