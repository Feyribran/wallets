import { Injectable } from '@angular/core';

import {HttpClient} from "@angular/common/http";
import {Coin} from "./model/coins";

@Injectable()
export class PricingService {

  // Asynchrone
  coins: Coin[];

  constructor(public http: HttpClient) {

  }

  getColor(symbol) {

    let coin = this.coins.find(coin => coin.symbol === symbol);
    if (symbol !== 'USD'){
      return coin.up;
    } else {
      return 'black';
    }

  }

  loadPrices(){ //renvoie une promesse !
    let url = 'https://api.coinmarketcap.com/v1/ticker/';
    //let up;

    function mapper(coin){
      return{
        name: coin.name,
        symbol: coin.symbol,
        price: coin.price_usd,
        up: coin.percent_change_1h > 0 ? 'green':'red'
      }
    }


    return this.http.get(url)
      .toPromise()
      .then(internetCoins => (internetCoins as any).map(mapper))
      // ici mapper est une fonction mise en paramètre. Elle n'est pas
      // executée par moi donc pas de ()
      .then(joliCoins => {
        this.coins = joliCoins;
        return joliCoins;
      });
  }


updatePrices(){

}


  priceToDollar(quantity, symbol){

  for (let coin of this.coins) {
      if (coin.symbol !== 'USD'){
        if (coin.symbol == symbol){
          let result = quantity * coin.price;
          return result;
        }
      }


    }
    // return 0;
    throw symbol + ' not found';
  }
}
