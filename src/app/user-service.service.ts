import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient) { }

  getUser(user: User) {
    return this.http.get(user.id)
    // Fix this later should fetch user given ID from API
  }

  addUser(user: User) {
    return this.http.post(user.id, user)
    // Should send a user to the database given a user ID and email
  }
}
