package com.jvm_bloggers.frontend.public_area.blogs;

import com.jvm_bloggers.entities.blog_post.BlogPost;
import com.jvm_bloggers.frontend.admin_area.blogs.BlogPostsPageRequestHandler;
import com.jvm_bloggers.frontend.common_components.infinite_scroll.InfinitePaginationPanel;
import com.jvm_bloggers.frontend.public_area.AbstractFrontendPage;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import static com.jvm_bloggers.frontend.public_area.blogs.BlogPostsPage.BLOG_ID_PARAM;
import static com.jvm_bloggers.utils.DateTimeUtilities.DATE_TIME_FORMATTER;

@MountPath("blogs/${" + BLOG_ID_PARAM + "}/posts")
public class BlogPostsPage extends AbstractFrontendPage {

    static final String BLOG_ID_PARAM = "blogId";
    static final String DATA_VIEW_ID = "pageable";
    static final String DATA_VIEW_WRAPPER_ID = "pageable-wrapper";
    static final String INFINITE_SCROLL_ID = "infinite-pager";

    @SpringBean
    private BlogsPageBackingBean blogsPageBackingBean;

    private final BlogPostsPageRequestHandler requestHandler;

    public BlogPostsPage(PageParameters parameters) {
        requestHandler = blogsPageBackingBean.blogPostsRequestHandler(
            parameters.get(BLOG_ID_PARAM).toLong(-1));
        WebMarkupContainer pageableWrapper = new WebMarkupContainer(DATA_VIEW_WRAPPER_ID);
        add(pageableWrapper);
        DataView dataView = createBlogPostDataView();
        pageableWrapper.add(dataView);
        pageableWrapper.add(new InfinitePaginationPanel(INFINITE_SCROLL_ID,
            pageableWrapper, dataView));
    }

    private DataView<BlogPost> createBlogPostDataView() {
        final DataView<BlogPost> dataView =
            new DataView<BlogPost>(DATA_VIEW_ID, requestHandler) {
                @Override
                protected void populateItem(Item<BlogPost> item) {
                    BlogPost post = item.getModelObject();
                    item.add(new ExternalLink("postLink", post.getUrl(), post.getTitle()));
                    item.add(new Label("publishedDate", post.getPublishedDate().format(DATE_TIME_FORMATTER)));
                }
            };

        dataView.setItemsPerPage(blogsPageBackingBean.getDefaultPageSize());
        dataView.setOutputMarkupId(true);
        return dataView;
    }
}
