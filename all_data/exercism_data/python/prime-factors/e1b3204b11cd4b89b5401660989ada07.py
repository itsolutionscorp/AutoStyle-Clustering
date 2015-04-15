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
        if not number % p:
            return p

    jumps = [4, 2, 4, 2, 4, 6, 2, 6]
    candidate = 7
    check_until = int(math.sqrt(number))

    for jump in itertools.cycle(jumps):
        if not number % candidate:
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

        while not number % factor:
            factors.append(factor)
            number //= factor

    return factors
