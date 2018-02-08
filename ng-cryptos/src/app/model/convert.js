//require only exists in NodeJS : 2011
const coins = require('./coins');

//console.log(coins);


function priceToDollar(quantity, symbol) {

    for (let index = 0; index < coins.length; index++) {
        let coin = coins[index];
        if (coin.symbol == symbol) {
            let result = quantity * coin.price;
            return result;
        }
    }
    // return 0;
    throw symbol + ' not found';
}

let total = priceToDollar(3.45, 'XRP')
console.log(total);

let myWallet = [
    {
        symbol: 'BTC',
        quantity: 1.2
    },
    {
        symbol: 'XRP',
        quantity: 250
    },
    {
        symbol: 'BCH',
        quantity: 0.3
    }
];

//considering coins is global.
// Not Pure
// (car tous les arguments de la fonction ne sont pas dans la fonction, comme coins)
function walletToDollar(wallet){

    let result = 0;
    for (let index = 0; index < wallet.length; index ++){
        let line = wallet[index];
        result += priceToDollar(line.quantity, line.symbol);
    }
    return result;
}

let walletPrice = walletToDollar(myWallet);
console.log(walletPrice);

function priceToMoney(quantity,symbol,resultSymbol){
    let inter = priceToDollar(quantity,symbol);

    for (let index = 0; index < coins.length; index++){
        let coin = coins[index];
        if (resultSymbol == coin.symbol){
            let result = inter/coin.price;
            return result;
        }
    }
}


let convert = priceToMoney(2, 'BTC', 'ETH');
console.log(convert);


function walletToBitcoin(wallet){
    let inter = walletToDollar(wallet);
    for (let index = 0; index < coins.length; index++){

        if (coins[index].symbol == 'BTC'){
            let result = inter / coins[0].price;
            return result;
        }
    }
}


let walletPriceBitcoin = walletToBitcoin(myWallet);
console.log(walletPriceBitcoin);