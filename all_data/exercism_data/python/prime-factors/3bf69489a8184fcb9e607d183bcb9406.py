def prime_factors(num):
    curr = 2
    primes = []
    while num > 1:
        qtd, rem = divmod(num,curr)
        if not rem:
            primes.append(curr)
            num = qtd
        else:
            curr += 1
            
    return primes
