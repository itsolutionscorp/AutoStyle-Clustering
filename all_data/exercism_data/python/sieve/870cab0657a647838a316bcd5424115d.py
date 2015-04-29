import math

def sieve(n):
    a = [a for a in range(2, n + 1)]
    
    for i in a:
        if i <= math.sqrt(n):
            a = [b for b 
                 in a 
                 if b == i or b % i != 0]
        
    return a     
