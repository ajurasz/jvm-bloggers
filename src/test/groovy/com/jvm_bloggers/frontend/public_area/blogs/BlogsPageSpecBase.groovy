package com.jvm_bloggers.frontend.public_area.blogs

import com.jvm_bloggers.MockSpringContextAwareSpecification
import com.jvm_bloggers.domain.query.blog_statistics_for_listing.BlogStatisticsForListingQuery
import com.jvm_bloggers.entities.blog.BlogRepository
import com.jvm_bloggers.entities.blog_post.BlogPostRepository
import com.jvm_bloggers.frontend.admin_area.PaginationConfiguration
import com.jvm_bloggers.frontend.public_area.common_layout.RightFrontendSidebarBackingBean

class BlogsPageSpecBase extends MockSpringContextAwareSpecification {

    BlogRepository blogRepository = Mock()
    BlogPostRepository blogPostRepository = Mock()
    BlogStatisticsForListingQuery blogStatisticsForListingQuery = Mock()
    BlogWithStatisticsItemPopulator blogWithStatisticsItemPopulator = new BlogWithStatisticsItemPopulator()
    PaginationConfiguration paginationConfiguration = new PaginationConfiguration(2)
    RightFrontendSidebarBackingBean sidebarBackingBean = Stub()

    @Override
    protected void setupContext() {
        addBean(blogRepository)
        addBean(blogPostRepository)
        addBean(blogStatisticsForListingQuery)
        addBean(blogWithStatisticsItemPopulator)
        addBean(paginationConfiguration)
        addBean(sidebarBackingBean)
    }
}
