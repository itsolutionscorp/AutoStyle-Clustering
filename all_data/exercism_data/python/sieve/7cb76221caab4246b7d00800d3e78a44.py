def _create_sieve(max):
    """ Create Sieve of Eratosthenes, pad with 2 non primes(0, 1) for easier indexing. """
    return [False, False] + [True] * (max - 2)

def _skip_non_primes(number, sieve):
    """ Advance index until unmarked number is found. """
    while number < len(sieve):
        if sieve[number]:
            break
        number += 1
    return number

def sieve(max):
    """ Use Sieve of Eratosthenes to return all prime numbers smaller max. """
    prime_sieve = _create_sieve(max)
    current_number = 2
    while current_number < max:
        for n in xrange(2, max):
            multiple = current_number * n
            if multiple >= max:
                break
            prime_sieve[multiple] = False
        current_number = _skip_non_primes(current_number + 1, prime_sieve)
    return [i for i, is_prime in enumerate(prime_sieve) if is_prime]
