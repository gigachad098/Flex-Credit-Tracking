import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
// Import the module from the SDK
import { AuthModule } from '@auth0/auth0-angular';
import { environment } from 'src/environments/environment';
import { HomepageComponent } from './homepage/homepage.component';
import { FlexInfoComponent } from './flex-info/flex-info.component';
import { SpendingListComponent } from './spending-list/spending-list.component';
import { AuthService } from '@auth0/auth0-angular';
import { LayoutComponent } from './layout/layout.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    HomepageComponent,
    FlexInfoComponent,
    SpendingListComponent,
    LayoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    AuthModule.forRoot({
      domain: 'flextracking.us.auth0.com',
      clientId: 'lvPYTOdRHha2RlQWDBMtxT3moBMo0xoS',
      authorizationParams: {
        redirect_uri: window.location.origin
      }
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }