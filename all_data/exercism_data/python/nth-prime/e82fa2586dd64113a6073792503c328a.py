from itertools import count, islice


def nth_prime(nth):
    """Returns the nth prime."""
    if nth < 1: raise ValueError("Parameter nth must be 1 or higher")
    for prime in islice(generate_prime_numbers(), 0, nth): pass
    return prime

def generate_prime_numbers( ):
    """Generates an endless stream of prime numbers."""

    # This prime number generator is based on the sieve of Eratosthenes.
    # The standard implementation of the sieve flags all compounds for a prime,
    # up to a given number. This implementation of the sieve works without
    # such number, by only flagging the first upcoming compound for a prime
    # and moving it to the next compound when it is hit by a prime candidate.
    # This store holds the relation: {next upcoming compound: for prime}.
    compounds = {  }

    # 2 is the only even prime number. The rest of the code has been optimized
    # for this by only checking the odd numbers.
    yield 2

    for candidate in count(3, 2):

        # When the prime candidate exists in the compounds store, then it
        # is not a prime, but a compound for an already identified prime.
        # In such case, we move the compound value forward to the next
        # upcoming compound value for the prime that was hit.
        if candidate in compounds:

            prime = compounds[candidate]

            # Step size of two * the prime number, since even-numbered
            # candidates are skipped.
            step = 2 * prime
            next_compound = candidate + step

            # Skip over compounds that already exist in the store. These might
            # have been registered for lower prime numbers already, in which
            # case the lower prime must be left untouched to not lose its
            # resolution in the compound flagging.
            while next_compound in compounds:
                next_compound += step

            # Register the next upcoming compound for the prime.
            del(compounds[candidate])
            compounds[next_compound] = prime 

        # The candidate does not exist in the store, so it is a prime number.
        # Register candidate * candidate as the next upcoming compound for this
        # prime (since all numbers below candidate have already been processed).
        else:
            yield candidate 
            compounds[candidate * candidate] = candidate
