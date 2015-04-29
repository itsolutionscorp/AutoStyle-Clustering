def prime_factors(number):
    factors = []
    n = number
    i = 2
    while n > 1:
        if n % i == 0:
            factors.append(i)
            n //= i
            i = 2
        else:
            i += 1
    return factors
