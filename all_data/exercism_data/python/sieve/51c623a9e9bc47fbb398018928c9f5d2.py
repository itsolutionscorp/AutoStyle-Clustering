def sieve( N ):
    
    truthValues = {}
    for n in range( 2, N+1 ):
        truthValues[ n ] = True

    primes = []
    for n in range( 2, N+1 ):
        if truthValues[ n ] == True:
            primes.append( n )
            for k in range(2, int( N/n )+1 ):
                truthValues[ k*n ] = False

    return primes
