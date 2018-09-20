package com.jtanveer.fastex.di.component;

import android.app.Application;

import com.jtanveer.fastex.App;
import com.jtanveer.fastex.di.module.ActivityModule;
import com.jtanveer.fastex.di.module.AppModule;
import com.jtanveer.fastex.di.module.FragmentModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules={ActivityModule.class, FragmentModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
