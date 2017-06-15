package com.jvm_bloggers.frontend.public_area.blogs

import com.jvm_bloggers.frontend.common_components.infinite_scroll.InfinitePaginationPanel
import com.jvm_bloggers.frontend.public_area.blogs.navigation.NavigationTabItem
import org.apache.wicket.markup.repeater.data.DataView
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.util.tester.TagTester

import static com.jvm_bloggers.frontend.public_area.blogs.AbstractBlogsPage.*
import static com.jvm_bloggers.frontend.public_area.blogs.navigation.NavigationTabItem.ACTIVE_CSS_CLASS

class CompanyBlogsPageSpec extends BlogsPageSpecBase {

    def "Should render page with all it's components"() {
        when:
        tester.startPage(CompanyBlogsPage)

        then:
        tester.assertComponent(COMPANY_TAB_ID, NavigationTabItem)
        tester.assertBookmarkablePageLink("$COMPANY_TAB_ID:link", CompanyBlogsPage, new PageParameters())
        tester.assertComponent("$DATA_VIEW_WRAPPER_ID:$DATA_VIEW_ID", DataView)
        tester.assertComponent("$DATA_VIEW_WRAPPER_ID:$INFINITE_SCROLL_ID", InfinitePaginationPanel)
    }

    def "Should set page tab as active"() {
        when:
        tester.startPage(CompanyBlogsPage)

        then:
        TagTester tagTester = tester.getTagByWicketId(COMPANY_TAB_ID)
        tagTester.getAttribute("class").contains(ACTIVE_CSS_CLASS)
    }
}
