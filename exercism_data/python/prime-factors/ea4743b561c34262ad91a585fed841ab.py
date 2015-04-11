from math import sqrt

def prime_factors( N ):

    if N == 1:
        return []
    
    factors = []
    f = 2
    while f < sqrt( N+1):
        if N % f == 0:
            factors.append( f )
            N = int( N / f )
        else:
            f += 1
    factors.append( N )
    return factors
