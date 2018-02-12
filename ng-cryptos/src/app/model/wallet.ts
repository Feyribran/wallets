
import {PricingService} from "../pricing.service";
import {User} from "./user";

export class Line{

    constructor(public symbol: string, public quantity: number, public value: number) {

    }
}



export class Wallet{
    user?: User;
    lines?: Line[] = [];
    name: string;
    pricingService:PricingService;




    deposit(dollars: number){
        let lineDollars = this.lines.find(line => line.symbol === 'USD');
        if (lineDollars){
            lineDollars.quantity += dollars;
            lineDollars.value += dollars;
        } else {
            this.lines.push(new Line('USD', dollars, dollars));
        }
    }


    buy(quantity: number, symbol: string){
        let valueBought = this.pricingService.priceToDollar(quantity, symbol);
        let lineDollars = this.lines.find(line => line.symbol === 'USD');
        let lineCrypto = this.lines.find(line => line.symbol === symbol);

        if (lineDollars.quantity > valueBought){
            // maj de la quantité de dollars
            lineDollars.quantity -= valueBought;
            lineDollars.value = lineDollars.quantity;
            // new line en 'symbol'
          if (lineCrypto){
            lineCrypto.quantity += quantity;
            lineCrypto.value = this.pricingService.priceToDollar(lineCrypto.quantity,lineCrypto.symbol)
          } else {
            this.lines.push(new Line(symbol, quantity, this.pricingService.priceToDollar(quantity,symbol)));
          }

        }
    }

    sell(quantity: number, symbol: string){
       let line = this.lines.find(line => line.symbol === symbol);

        if (line.quantity > 0){
            //let soldValue = this.pricingService.priceToDollar(quantity, symbol);
            this.deposit((line.value/line.quantity)*quantity);

            line.quantity -= quantity;
            line.value = this.pricingService.priceToDollar(line.quantity,line.symbol);



        } else {
            console.log('T\'as pas les thunes gros');
        }
/*
      if (line.quantity === 0){
        let index = this.lines.indexOf(line);
        this.lines.splice(index,1);
      }*/

    }



  totalToDollar(){
    let result = 0;
    //let lineDollars = this.lines.find(line => line.symbol === 'USD')
    for (let line of this.lines){

        result += line.value ;
        // console.log(result);
      }



    return result;

/*
    this.lines.reduce(function (total, line) {
     return line.symbol === 'USD' ?
     total + line.quantity :
     total + line.value;
     }, 0);*/
  }



 getLinesOrderedByValue(){

   let walletOrdered = [];
   //let lineDollars = this.lines.find(line => line.symbol === 'USD');

   if (this.lines.find(line => line.symbol !== 'USD')) {
     walletOrdered = this.lines
       .sort(function(a,b){return a.value-b.value});
   }
  }


}



