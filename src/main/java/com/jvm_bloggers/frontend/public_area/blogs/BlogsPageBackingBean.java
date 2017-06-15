package com.jvm_bloggers.frontend.public_area.blogs;

import com.jvm_bloggers.domain.query.blog_statistics_for_listing.BlogStatisticsForListingQuery;
import com.jvm_bloggers.entities.blog.BlogRepository;
import com.jvm_bloggers.entities.blog.BlogType;
import com.jvm_bloggers.entities.blog_post.BlogPostRepository;
import com.jvm_bloggers.frontend.admin_area.PaginationConfiguration;
import com.jvm_bloggers.frontend.admin_area.blogs.BlogPostsPageRequestHandler;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BlogsPageBackingBean {

    private final BlogRepository blogRepository;

    private final BlogPostRepository blogPostRepository;

    private final BlogStatisticsForListingQuery blogStatsForListingQuery;

    private final BlogWithStatisticsItemPopulator blogWithStatisticsItemPopulator;

    private final PaginationConfiguration paginationConfiguration;

    public int getDefaultPageSize() {
        return paginationConfiguration.getDefaultPageSize();
    }

    public BlogPostsPageRequestHandler blogPostsRequestHandler(Long blogId) {
        return new BlogPostsPageRequestHandler(paginationConfiguration, blogPostRepository,
            blogRepository, blogId);
    }

    public BlogsRequestHandler companyRequestHandler() {
        return new BlogsRequestHandler(blogStatsForListingQuery,
            blogRepository, paginationConfiguration, BlogType.COMPANY);
    }

    public BlogsRequestHandler personalRequestHandler() {
        return new BlogsRequestHandler(blogStatsForListingQuery,
            blogRepository, paginationConfiguration, BlogType.PERSONAL);
    }

    public BlogsRequestHandler videoRequestHandler() {
        return new BlogsRequestHandler(blogStatsForListingQuery,
            blogRepository, paginationConfiguration, BlogType.VIDEOS);
    }

    public BlogWithStatisticsItemPopulator blogWithStatisticsItemPopulator() {
        return blogWithStatisticsItemPopulator;
    }
}
