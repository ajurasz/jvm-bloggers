package com.jvm_bloggers.frontend.public_area.blogs;

import com.jvm_bloggers.entities.blog.BlogType;

import org.wicketstuff.annotation.mount.MountPath;

@MountPath("blogs/company")
public class CompanyBlogsPage extends AbstractBlogsPage {

    @Override
    protected Class<? extends AbstractBlogsPage> getActiveClass() {
        return this.getClass();
    }

    @Override
    protected BlogsRequestHandler getBlogsRequestHandler() {
        return new BlogsRequestHandler(blogStatsForListingQuery, blogRepository,
            paginationConfiguration, BlogType.COMPANY);
    }
}
