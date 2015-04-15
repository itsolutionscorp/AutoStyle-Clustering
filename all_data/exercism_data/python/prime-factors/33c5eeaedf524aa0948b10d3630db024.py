"""Finds the prime factors of a number"""

def prime_factors(number):
    """Finds prime factors of a number"""
    factors = []
    for prime in primes(int(number**0.5)):
        while number % prime == 0:
            number /= prime
            factors.append(prime)
            if number == 1:
                break
    if number != 1:
        factors += [number]
    return factors

def primes(lim):
    """Yields prime numbers"""
    numbers = [True] * (lim + 1)
    for num in range(2, (lim + 1)):
        if numbers[num]:
            yield num
            for mul in range(num * num, lim, num):
                numbers[mul] = False
