 

from itertools import izip_longest

def distance(y,x):
    if len(x)!=len(y):
        return 0
    
    return  sum(1 for i,ii in izip_longest(y,x) if i!=ii )
 
