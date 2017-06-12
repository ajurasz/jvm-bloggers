package com.jvm_bloggers.frontend.public_area.blogs;

import org.wicketstuff.annotation.mount.MountPath;

@MountPath(value = "blogs", alt = "blogs/personal")
public class PersonalBlogsPage extends AbstractBlogsPage {

    @Override
    protected Class<? extends AbstractBlogsPage> getActiveClass() {
        return this.getClass();
    }

    @Override
    protected BlogsRequestHandler getBlogsRequestHandler() {
        return blogsPageBackingBean.personalRequestHandler();
    }
}
