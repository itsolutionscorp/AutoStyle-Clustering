def sieve(limit):
    primes = []
    vals = range(2,limit+1)
    
    while vals:
        # Pop lowest prime from vals and append to primes
        primes.append(vals.pop(0))
        # Remove multiples of latest prime from vals
        for item in vals:
            if item % primes[-1] == 0:
                vals.remove(item)
                
    return primes
    
