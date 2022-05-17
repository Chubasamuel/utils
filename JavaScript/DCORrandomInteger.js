/***
Code written by Jeremiah Chuba Samuel.

This programme generates unique or duplicate random integers.
>> @var resultArray = generateRandom(minimumInteger,maximumInteger,howMany,allowDuplicate);
>> 4 arguments, first 3 should be integers while the fourth is a @boolean with value true or false.

To generate unique Integers, ((maximumInteger-minimumInteger)+1) must not be greater, but rather must be the same as the value provided for howMany OR it could be lesser or equal, but must be greater than 0;

***/
const genRan=(a)=>{return Math.floor(Math.random()*a); }

const generateRandom=(min,max,howMany,allowDuplicate)=>{
let tempRe=0;
let mi=parseInt(min); 
let ma= parseInt(max); 
let ho=parseInt(howMany); 
let aL=allowDuplicate;
let count=0;
let result=[];
let tempC;
let tempH;

if(aL){
tempRe=genRan(ma);

while(tempRe<mi||tempRe>ma||result.length<ho){
tempRe=genRan(ma);
if(tempRe>=mi||tempRe<=ma){
    result[count]=tempRe;
    count+=1;
 }
}
}
else if(!aL){
tempC=parseInt(max)-parseInt(min);
tempH=parseInt(howMany);
if((tempC+1===tempH)||((tempH<=tempC)&&tempH>0)){

while(tempRe<mi||tempRe>ma||result.length<ho){
tempRe=genRan(ma+1);
if((tempRe>=mi||tempRe<=ma)&&(result.indexOf(tempRe)==-1)){
    result[count]=tempRe;
count+=1; }
}
}
else{
result=[];}
}
else{
result=[];}
//}
return result;
}

export{ generateRandom}
//console.log(generateRandom(1,1000,60,"false"))



