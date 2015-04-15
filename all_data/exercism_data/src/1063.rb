def compute(x,y)
 counter =0;
 i=0;
   while i < x.length && i<y.length  
     if x[i] != y[i]
      counter +=1   
      end
    i+=1
 end
return  counter
 end