import { Component } from '@angular/core';

@Component({
  selector: 'app-firstlogin',
  templateUrl: './firstlogin.component.html',
  styleUrls: ['./firstlogin.component.css']
})
export class FirstloginComponent {
  displayModal!: boolean;
  constructor() {
    this.displayModal = true
  }
}
