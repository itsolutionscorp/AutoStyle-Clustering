def sieve(max_n):
    '''
    Generate list of prime numbers up to max_n
    '''
    numbers = range(3, max_n+1, 2)
    half = (max_n)//2
    initial = 4

    for step in xrange(3, max_n+1, 2):
        for i in xrange(initial, half, step):
            numbers[i-1] = 0
        initial += 2*(step+1)

        if initial > half:
            return [2] + filter(None, numbers)

def prime_factors(n):
    factors = []
    max_sieve = max(int(round(n ** (1/2.0),0)), 3)
    primes = sieve(max_sieve) # Get list of primes up to square root of n
    primes_len = len(primes)
    i = 0
    while n > 1 and i < primes_len:
        p = primes[i]
        if n % p == 0:
            n = n / p
            factors.append(p)
        else:
            i = i + 1
    if i >= primes_len:
        factors.append(n) # n is a prime itself
    return factors

if __name__ == '__main__':
    print prime_factors(6)
