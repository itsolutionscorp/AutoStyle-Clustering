def sieve(number):
    nonprimes = []
    primes = []
    for x in range(2,number/2):
        y = 2
        result = 0
        while (result < number +1):
            result = x*y
            nonprimes.append(result)
            y = y + 1

    for a in range(2,number):
        if a not in nonprimes:
            primes.append(a)
    return primes
