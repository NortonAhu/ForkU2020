package com.bluecup.hongyu.mocku2020;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_上午10:59
 */
final class Module {
    static Object[] list(App app) {
        return new Object[]{new AppModule(app)};
    }

    private Module() {
    }
}
