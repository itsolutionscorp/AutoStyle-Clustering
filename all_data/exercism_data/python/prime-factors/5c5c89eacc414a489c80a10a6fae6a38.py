def prime_factors(number):
    """Return list of prime factors for the given number."""
    primes = []
    divisor = 2
    while number > 1:
        if not number % divisor:
            number /= divisor
            primes.append(divisor)
        else:
            # Skip even numbers for divisor > 2
            # to speed up the calculation:
            divisor += 1 if divisor == 2 else 2
    return primes
