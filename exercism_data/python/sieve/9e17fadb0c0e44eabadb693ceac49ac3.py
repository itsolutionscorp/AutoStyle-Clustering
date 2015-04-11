def sieve(limit):
    is_prime = [True] * limit
    is_prime[0:2] = [False, False]
    for num in range(0, int(limit**0.5+1)):
        if is_prime[num]:
            for n in range(num**2, limit, num):
                is_prime[n] = False
    return [n for n in range(0,limit) if is_prime[n]]
