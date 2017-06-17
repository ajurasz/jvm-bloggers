package com.jvm_bloggers.frontend.public_area.blogs;

import com.jvm_bloggers.entities.blog.BlogRepository;
import com.jvm_bloggers.entities.blog_post.BlogPost;
import com.jvm_bloggers.entities.blog_post.BlogPostRepository;
import com.jvm_bloggers.frontend.admin_area.PaginationConfiguration;
import com.jvm_bloggers.frontend.admin_area.blogs.BlogPostsPageRequestHandler;
import com.jvm_bloggers.frontend.common_components.infinite_scroll.InfinitePaginationPanel;
import com.jvm_bloggers.frontend.public_area.AbstractFrontendPage;
import com.jvm_bloggers.frontend.public_area.blogs.timeline_panel.BlogPostsTimelinePanel;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.wicketstuff.annotation.mount.MountPath;

import static com.jvm_bloggers.frontend.public_area.blogs.BlogPostsPage.BLOG_ID_PARAM;
import static com.jvm_bloggers.utils.DateTimeUtilities.DATE_TIME_FORMATTER;

@MountPath("blogs/${" + BLOG_ID_PARAM + "}/posts")
public class BlogPostsPage extends AbstractFrontendPage {

    static final String BLOG_ID_PARAM = "blogId";

    static final String AUTHOR_NAME_PARAM = "authorName";

    @SpringBean
    PaginationConfiguration paginationConfiguration;

    @SpringBean
    BlogRepository blogRepository;

    @SpringBean
    BlogPostRepository blogPostRepository;

    public BlogPostsPage(PageParameters parameters) {
        BlogPostsPageRequestHandler requestHandler = new BlogPostsPageRequestHandler(
            paginationConfiguration,
            blogPostRepository,
            blogRepository,
            parameters.get(BLOG_ID_PARAM).toLong(-1));
        Label authorLabel = new Label("author",
            parameters.get(AUTHOR_NAME_PARAM).toOptionalString());
        authorLabel.setVisible(!parameters.get(AUTHOR_NAME_PARAM).isEmpty());
        add(authorLabel);
        add(new BlogPostsTimelinePanel("timeline", requestHandler));
    }
}
