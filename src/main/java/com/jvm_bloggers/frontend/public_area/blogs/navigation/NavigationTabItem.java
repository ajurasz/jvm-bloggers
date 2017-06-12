package com.jvm_bloggers.frontend.public_area.blogs.navigation;

import org.apache.wicket.Page;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class NavigationTabItem extends Panel {

    public static final String ACTIVE_CSS_CLASS = "active";

    private final Class<? extends Page> targetPageClass;

    private final Class<? extends Page> currentPageClass;

    public NavigationTabItem(String id,
                             Class<? extends Page> targetPageClass,
                             Class<? extends Page> currentPageClass) {
        super(id);
        this.targetPageClass = targetPageClass;
        this.currentPageClass = currentPageClass;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Link link = new BookmarkablePageLink<>("link", targetPageClass);
        link.setBody(Model.of(findMarkupStream().next().toString()));
        add(link);
        if (targetPageClass.equals(currentPageClass)) {
            add(new AttributeAppender("class", ACTIVE_CSS_CLASS));
        }
    }
}
