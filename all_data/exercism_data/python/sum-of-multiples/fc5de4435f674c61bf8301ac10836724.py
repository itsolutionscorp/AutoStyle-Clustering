import numpy as np

def sum_of_multiples(n, base=[3, 5]):
    
    if 0 in base:
        base.remove(0)
    
    s = set([])
    for b in base:
        s = set.union(s, np.arange(b, n, b))
        
    return sum(s)
