import { Component, OnInit } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  currentUser: import("@auth0/auth0-angular").User | null | undefined;
  
  constructor(public auth: AuthService) {}
  

  ngOnInit(): void {
    this.auth.user$.subscribe(userProfile => {
      this.currentUser = userProfile;
      console.log(this.currentUser);
    });
  }

}
