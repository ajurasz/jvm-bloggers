package com.jvm_bloggers.frontend.public_area.blogs;

import com.jvm_bloggers.domain.query.blog_statistics_for_listing.BlogStatisticsForListing;
import com.jvm_bloggers.frontend.common_components.infinite_scroll.InfinitePaginationPanel;
import com.jvm_bloggers.frontend.public_area.AbstractFrontendPage;
import com.jvm_bloggers.frontend.public_area.blogs.navigation.NavigationTabItem;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.spring.injection.annot.SpringBean;

public abstract class AbstractBlogsPage extends AbstractFrontendPage {

    static final String DATA_VIEW_ID = "pageable";
    static final String DATA_VIEW_WRAPPER_ID = "pageable-wrapper";
    static final String INFINITE_SCROLL_ID = "infinite-pager";
    static final String PERSONAL_TAB_ID = "personal";
    static final String COMPANY_TAB_ID = "company";
    static final String VIDEO_TAB_ID = "video";

    @SpringBean
    protected BlogsPageBackingBean blogsPageBackingBean;

    protected abstract BlogsRequestHandler getBlogsRequestHandler();

    protected abstract Class<? extends AbstractBlogsPage> getActiveClass();

    AbstractBlogsPage() {
        createNavigationItems();
        WebMarkupContainer pageableWrapper = new WebMarkupContainer(DATA_VIEW_WRAPPER_ID);
        add(pageableWrapper);
        DataView dataView = createBlogChannelList(DATA_VIEW_ID);
        pageableWrapper.add(dataView);
        pageableWrapper.add(new InfinitePaginationPanel(INFINITE_SCROLL_ID,
            pageableWrapper, dataView));
    }

    private void createNavigationItems() {
        add(new NavigationTabItem(PERSONAL_TAB_ID, PersonalBlogsPage.class, this.getActiveClass()));
        add(new NavigationTabItem(COMPANY_TAB_ID, CompanyBlogsPage.class, this.getActiveClass()));
        add(new NavigationTabItem(VIDEO_TAB_ID, VideoBlogsPage.class, this.getActiveClass()));
    }

    private DataView<BlogStatisticsForListing> createBlogChannelList(String id) {
        final DataView<BlogStatisticsForListing> dataView =
            new DataView<BlogStatisticsForListing>(id, getBlogsRequestHandler()) {
                @Override
                protected void populateItem(Item<BlogStatisticsForListing> item) {
                    blogsPageBackingBean.blogWithStatisticsItemPopulator().populateItem(item);
                }
            };

        dataView.setItemsPerPage(blogsPageBackingBean.getDefaultPageSize());
        dataView.setOutputMarkupId(true);
        return dataView;
    }
}
