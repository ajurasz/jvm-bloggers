package com.jvm_bloggers.frontend.public_area.blogs

import com.jvm_bloggers.frontend.common_components.infinite_scroll.InfinitePaginationPanel
import com.jvm_bloggers.frontend.public_area.blogs.navigation.NavigationTabItem
import org.apache.wicket.markup.repeater.data.DataView
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.util.tester.TagTester

import static com.jvm_bloggers.frontend.public_area.blogs.AbstractBlogsPage.*
import static com.jvm_bloggers.frontend.public_area.blogs.navigation.NavigationTabItem.ACTIVE_CSS_CLASS

class VideoBlogsPageSpec extends BlogsPageSpecBase {

    def "Should render page with all it's components"() {
        when:
        tester.startPage(VideoBlogsPage)

        then:
        tester.assertComponent(VIDEO_TAB_ID, NavigationTabItem)
        tester.assertBookmarkablePageLink("$VIDEO_TAB_ID:link", VideoBlogsPage, new PageParameters())
        tester.assertComponent("$DATA_VIEW_WRAPPER_ID:$DATA_VIEW_ID", DataView)
        tester.assertComponent("$DATA_VIEW_WRAPPER_ID:$INFINITE_SCROLL_ID", InfinitePaginationPanel)
    }

    def "Should set page tab as active"() {
        when:
        tester.startPage(VideoBlogsPage)

        then:
        TagTester tagTester = tester.getTagByWicketId(VIDEO_TAB_ID)
        tagTester.getAttribute("class").contains(ACTIVE_CSS_CLASS)
    }
}
