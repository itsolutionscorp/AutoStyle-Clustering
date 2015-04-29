def prime_factors(number):
    i = 2
    factors = []
    while i * i <= number:
        while not number % i:
            factors.append(i)
            number = number / i
        i += 1
    if number != 1:
        factors.append(number)
    return factors
