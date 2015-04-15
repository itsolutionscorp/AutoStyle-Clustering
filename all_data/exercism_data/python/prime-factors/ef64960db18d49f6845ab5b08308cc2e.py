import itertools
import math

def trial_division(number):
    """
    trial_division(int) -> int

    Return the smallest factor of number.
    """

    if number == 1:
        return 1

    for p in (2, 3, 5):
        if number % p == 0:
            return p

    jumps = [4, 2, 4, 2, 4, 6, 2, 6]
    candidate = 7
    check_until = int(math.sqrt(number))
    
    for jump in itertools.cycle(jumps):
        if number % candidate == 0:
            return candidate

        candidate += jump

        if candidate > check_until:
            break

    return number


def prime_factors(number):
    """
    prime_factors(int) -> list of ints

    Return the list of ordered prime factors starting with the smallest.
    Uses trial division.
    """

    factors = []
    while number > 1:
        factor = trial_division(number)

        while number % factor == 0:
            factors.append(factor)
            number //= factor

    return factors
