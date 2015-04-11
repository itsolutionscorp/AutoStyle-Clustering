def prime_factors(num):
    primes = []
    prime = 2
    while num>1:
        if num%prime==0:
            primes += [prime]
            num /= prime
        else:
            prime += 1
    return primes
