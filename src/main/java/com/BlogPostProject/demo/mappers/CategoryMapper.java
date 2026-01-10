package com.BlogPostProject.demo.mappers;

import com.BlogPostProject.demo.domain.PostStatus;
import com.BlogPostProject.demo.domain.dtos.CategoryDto;
import com.BlogPostProject.demo.domain.dtos.CreateCategoryRequeust;
import com.BlogPostProject.demo.domain.entities.Category;
import com.BlogPostProject.demo.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequeust createCategoryRequeust);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts){
        if(null == posts){
            return 0;
        }
        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
