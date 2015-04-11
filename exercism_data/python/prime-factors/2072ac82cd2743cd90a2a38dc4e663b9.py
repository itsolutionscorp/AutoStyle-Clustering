import math

def prime_factors(number):
    factors = []
    while number % 2 == 0:
        factors.append(2)
        number = number / 2

    for i in range(3, int(math.sqrt(number)) + 1):
        while number % i == 0:
            factors.append(i)
            number = number / i

    if number > 2:
        factors.append(number)

    return factors
