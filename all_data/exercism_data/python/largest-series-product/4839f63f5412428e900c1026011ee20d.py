from operator import mul
    
def slices(series,num):
    slices=[] 
    
    if(len(series)<num):
        raise ValueError("series length should at least be the series length");
    
    for s in range(0,len(series)-num+1):
        slic=[]
        for i in range(s,s+num):
            slic.append(int(series[i]))
        slices.append(slic)
    return slices


def largest_product(series,num):
    largestSum=0
    
    for s in slices(series,num):
        if(reduce(mul,s, 1) >largestSum):
            largestSum=reduce(mul,s, 1)
    return largestSum
    
    
    



        
    
