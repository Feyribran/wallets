import { Injectable } from '@angular/core';
import {User} from "./model/user";
import {HttpClient} from "@angular/common/http";
import {Wallet} from "./model/wallet";
import {RequestOptions} from "@angular/http";

@Injectable()
export class DataService {

  constructor(public http:HttpClient) { }



  fetchUsers():Promise<User[]>{
    let url = 'http://localhost:8080/cryptos/api/users/';

    return this.http
      .get(url)
      .toPromise()
      .then(data => data as User[] );

  }

  fetchUsersWithWallets(user: User):Promise<User>{
    let url = 'http://localhost:8080/cryptos/api/users/'+user.id;
    return this.http
      .get(url)
      .toPromise()
      .then(data => {
        console.log('user with wallet : ', data);
        return data as User;
      })
  }

  createWallet(wallet:Wallet){
    let url = 'http://localhost:8080/cryptos/api/wallets/';
    let dto = { // Data Transfer Object pour Jax-B
      name: wallet.name,
      user: wallet.user
    }

    console.log('Sending walletDTO' + dto);

    // When posting, we send DATA to the url
    return this.http.post(url, dto)
      .toPromise()
      .then(data => console.log('Success :) ',data))

  }

  createUser(user:User){
    let url = 'http://localhost:8080/cryptos/api/users/';
    let dto = { // Data Transfer Object pour Jax-B
      name: user.name,
    }
    console.log('Creating user' + dto);

    return this.http.post(url, dto)
      .toPromise()
      .then(data => console.log('Success :)',data));
}

  deleteWallet(wallet:Wallet){
    let url = 'http://localhost:8080/cryptos/api/wallets/';


    console.log('Deleting wallet : ' + wallet.name);

    return this.http.delete(url,wallet)
      .toPromise()
      .then(data => console.log('Succeed to delete :)',data));
  }
}
