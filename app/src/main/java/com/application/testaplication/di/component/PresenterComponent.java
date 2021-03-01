package com.application.testaplication.di.component;

import com.application.testaplication.di.modules.PresentersModule;
import com.application.testaplication.di.scope.ActivityScope;
import com.application.testaplication.newsscreen.view.FirstActivity;
import com.application.testaplication.revisedscreen.view.RevisedActivity;
import com.application.testaplication.searchscreen.view.SearchActivity;


import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {PresentersModule.class})
public interface PresenterComponent {
     void inject(FirstActivity firstActivity);
     void inject(RevisedActivity revisedActivity);
     void inject(SearchActivity searchActivity);

}
