package com.aoo.admin.adapter.out.persistence;

import com.aoo.admin.adapter.out.persistence.mapper.CategoryMapper;
import com.aoo.admin.application.port.in.category.CreateCategoryResult;
import com.aoo.admin.application.port.in.category.DeleteCategoryResult;
import com.aoo.admin.application.port.in.category.SearchCategoryResult;
import com.aoo.admin.application.port.in.category.UpdateCategoryResult;
import com.aoo.admin.application.port.out.category.DeleteCategoryPort;
import com.aoo.admin.application.port.out.category.FindCategoryPort;
import com.aoo.admin.application.port.out.category.SaveCategoryPort;
import com.aoo.admin.application.port.out.category.UpdateCategoryPort;
import com.aoo.admin.application.service.AdminErrorCode;
import com.aoo.admin.application.service.AdminException;
import com.aoo.admin.domain.universe.UniverseCategory;
import com.aoo.common.adapter.out.persistence.entity.CategoryJpaEntity;
import com.aoo.common.adapter.out.persistence.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements SaveCategoryPort, FindCategoryPort, UpdateCategoryPort, DeleteCategoryPort {

    private final CategoryJpaRepository categoryJpaRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CreateCategoryResult save(String kor, String eng) {
        CategoryJpaEntity newCategory = CategoryJpaEntity.create(kor, eng);
        categoryJpaRepository.save(newCategory);

        return categoryMapper.mapToCreateCategoryResult(newCategory);
    }

    @Override
    public SearchCategoryResult findAll() {
        return categoryMapper.mapToSearchCategoryResult(categoryJpaRepository.findAll());
    }

    @Override
    public UniverseCategory findUniverseCategory(Long categoryId) {
        CategoryJpaEntity category = categoryJpaRepository.findById(categoryId)
                .orElseThrow(() -> new AdminException(AdminErrorCode.CATEGORY_NOT_FOUND));

        return categoryMapper.mapToUniverseCategory(category);
    }

    @Override
    public UpdateCategoryResult update(Long categoryId, String kor, String eng) {
        CategoryJpaEntity targetCategory = categoryJpaRepository.findById(categoryId)
                .orElseThrow(() -> new AdminException(AdminErrorCode.CATEGORY_NOT_FOUND));

        targetCategory.update(kor, eng);

        return categoryMapper.mapToUpdateCategoryResult(targetCategory);
    }

    @Override
    public DeleteCategoryResult delete(Long categoryId) {
        if (!categoryJpaRepository.existsById(categoryId)) throw new AdminException(AdminErrorCode.CATEGORY_NOT_FOUND);
        categoryJpaRepository.deleteById(categoryId);
        return DeleteCategoryResult.of(categoryId);
    }
}
