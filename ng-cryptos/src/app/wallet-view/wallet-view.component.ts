import { Component, OnInit } from '@angular/core';
import {Wallet} from "../model/wallet";
import {PricingService} from "../pricing.service";
//import {priceToDollar} from "../model/coins";

@Component({
  selector: 'app-wallet-view',
  templateUrl: './wallet-view.component.html',
  styleUrls: ['./wallet-view.component.css']
})
export class WalletViewComponent implements OnInit {

  money = 0;
  wallet = new Wallet();


  constructor(public pricingService:PricingService) {
      this.wallet.pricingService = pricingService;
      pricingService.loadPrices() //renvoie une promesse
        .then(data => console.log(data))
        .then(()=> this.initWallet()); // une fois que la réponse est reçue, affiche
  }

  ngOnInit() {
  }

  initWallet(){
    this.deposit('100000');
    this.buy('4','BTC');
    this.buy('20','XRP');
  }

  deposit(cash: string) {
    if (parseInt(cash) > 0) {
      this.wallet.deposit(parseInt(cash));
    } else if (parseInt(cash) == 0) {
      console.log('Tu m\'as pris pour un jambon ?');
    } else {
      console.log('Pas touch\' à mon flouz');
    }

  }

  buy(quantity: string, symbol: string) {
    if (parseInt(quantity) > 0) {
      this.wallet.buy(parseInt(quantity), symbol);
    }
  }

  sell(quantity: string, symbol: string) {
    if (parseInt(quantity) > 0) {
      this.wallet.sell(parseInt(quantity), symbol);
    }
  }

  getColor(symbol){
    return this.pricingService.getColor(symbol);
  }




}



