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

    jumps = [6, 4, 2, 4, 2, 4, 6, 2]
    jump_index = 0
    candidate = 7

    while candidate ** 2 <= number:
        if number%candidate == 0:
            return candidate

        jump_index = (jump_index + 1) % 8
        candidate += jumps[jump_index]

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
