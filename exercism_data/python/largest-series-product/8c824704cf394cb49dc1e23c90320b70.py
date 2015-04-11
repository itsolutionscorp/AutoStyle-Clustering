from functools import reduce
import operator

def prod(iterable):
    return reduce(operator.mul, iterable, 1)

def slices(series,n):

    length = len(series)
    if n > length or n == 0:
        raise ValueError ("requested series too long or zero")
    else:
        intlist = list(map(int, series))
        return list(intlist[i:i+n] for i in range(length-n+1))
    

def largest_product(series,n):
    
    largest=1
    if n==0:
        return largest 
    
    for i in slices(series,n):
        if prod(i) > largest:
            largest=prod(i)

    return largest
