import {coins} from '../../../../../cryptos/src/ts/coins';

let hasBigOne = coins.some(coin => coin.price > 10000);
//console.log('hasBigOne :', hasBigOne);

let hasBTG = coins.some(coin => coin.symbol === 'BTG');
//console.log('hasBTG :', hasBTG);

let hasXRP = coins
    .filter(coin => coin.price < 1)
    .some(coin => coin.symbol === 'XRP');

//console.log('hasXRP :', hasXRP);

function reducer (accu, coin){
    return accu + coin.price;
}
let marketValue = coins.reduce(reducer ,0);
console.log(marketValue);

function minReducer (min, coin){
    return min.price < coin.price ? min : coin;
}

let minValue = coins.reduce(minReducer ,coins[0]);
console.log(minValue);

function maxReducer(max, coin){
    return max.price > coin.price ? max : coin;
}

let maxValue = coins.reduce(maxReducer, coins[0]);
console.log(maxValue);
