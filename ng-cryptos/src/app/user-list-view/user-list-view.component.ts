import { Component, OnInit } from '@angular/core';
import {DataService} from "../data-service.service";
import {User} from "../model/user";
import {Wallet} from "../model/wallet";

@Component({
  selector: 'app-user-list-view',
  templateUrl: 'user-list-view.component.html',
  styleUrls: ['user-list-view.component.css']
})
export class UserListViewComponent implements OnInit {

  users:User[];
  selectedUser:User;
  createdWallet:Wallet = new Wallet();
  createdUser:User = new User();
  deletedWallet:Wallet;

  constructor(public dataService:DataService){

    dataService.fetchUsers()
      .then(users => this.users = users)
      .then(users => console.log('Users: ', users));

  }

  ngOnInit() {

  }

  details(user:User){
    this.selectedUser = user;

    this.createdWallet.user = user;
    this.createdWallet.name = user.name+ " 's wallet";
    console.log('You selected ', user);


    this.dataService
      .fetchUsersWithWallets(user)
      .then(fullUser => this.selectedUser = fullUser)
      .then(console.log);
  }

  createWallet(){
    return this.dataService.createWallet(this.createdWallet)
      .then( () => this.selectedUser.wallets.push(Object.assign({},this.createdWallet)))
      // ou ({... this.createdWallet}) /* splats destructuring */
      .catch(e => alert(e.message));
  }

  createUser() {
    return this.dataService.createUser(this.createdUser)
      .then (() => this.users.push(Object.assign({},this.createdUser)))
      .catch(e => alert(e.message));
  }

  deleteWallet() {
    return this.dataService.deleteWallet(this.deletedWallet)
      .then( () => this.selectedUser.wallets.splice(1));
  }
}
