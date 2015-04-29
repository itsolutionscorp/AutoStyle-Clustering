def prime_factors(n):
    factors = []

    while n > 1:
        for i in range(2, int(n/2) + 1):
            if n % i == 0:
                factors.append(i)
                n = n / i
                break
        else:
            factors.append(n)
            break

    return factors
