package com.jvm_bloggers.frontend.public_area.common_layout;

import com.googlecode.wicket.jquery.ui.markup.html.link.BookmarkablePageLink;
import com.jvm_bloggers.frontend.public_area.all_issues.AllIssuesPage;
import com.jvm_bloggers.frontend.public_area.blogs.PersonalBlogsPage;
import com.jvm_bloggers.frontend.public_area.contributors.ContributorsPage;

import org.apache.wicket.markup.html.panel.Panel;

public class HeaderFrontend extends Panel {

    public HeaderFrontend(String id) {
        super(id);
        add(new BookmarkablePageLink<>("blogs", PersonalBlogsPage.class));
        add(new BookmarkablePageLink<>("allIssues", AllIssuesPage.class));
        add(new BookmarkablePageLink<>("contributors", ContributorsPage.class));
    }
}
