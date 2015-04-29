import math

def prime_factors(n):
    factors = []
    j = int(n)
    for i in range(2, int(math.sqrt(n)) + 1):
        while j % i == 0:
            factors.append(i)
            j /= i
    if j > 1:
        factors.append(j)

    return factors
