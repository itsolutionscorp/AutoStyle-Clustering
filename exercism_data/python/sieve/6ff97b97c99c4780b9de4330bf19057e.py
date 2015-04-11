# -*- coding: utf-8 -*-
#sieve

def sieve(maxima):
    #create sieve
    s = [0] * maxima

    #loop min 2 to (half maxima)+1 - lazy way of handing odd maxima
    for i in range(2, (maxima // 2 + 1)):
        #now loop through whole sieve        
        for k in range(i*2, maxima, i):
            if s[k] == 0: s[k] = 1
            
    #find primes
    return [i for i in range(2, maxima) if s[i] == 0]
