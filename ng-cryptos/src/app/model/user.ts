import {Wallet} from "./wallet";
/**
 * Created by AELION on 09/02/2018.
 */
// Into the model

export class User {

  name: string;
    id: number;

    //protip: always better to initiate an array
    wallets: Wallet[] = [];

}
