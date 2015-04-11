def prime_factors(number):
    factors, n = [], 2
    while number > 1:
        while number % n == 0:
            factors.append(n)
            number /= n

        n += 1
    return factors
