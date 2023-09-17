import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './layout/header/header.component';

// Import the module from the SDK
import { AuthModule } from '@auth0/auth0-angular';
import { environment } from 'src/environments/environment';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    // Import the module into the application, with configuration
    AuthModule.forRoot(environment.auth)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }