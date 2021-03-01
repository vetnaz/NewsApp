package com.application.testaplication.di.component;

import com.application.testaplication.MyApplication;
import com.application.testaplication.di.modules.PresentersModule;

import dagger.Component;

@Component(modules = PresentersModule.class)
public interface AppComponent {
   void inject(MyApplication myApplication);
}
