def _sieve(N):
    """
        an incremental implementation of The Sieve of Eratosthenes

        this approach trades a little time for a lot of memory savings.

        as N->infinity, this will use more memory then a non-incremental
        implementation because this stores more information per composite. but
        in practice it uses much less memory because the look-ahead is stored
        in a sparse dynamically-allocated dict instead of a fixed-size list
        (which would need to be fully allocated and initalized at the start).

        this approach saves enough memory to handle at least N=93819012551
        without a MemoryError. in contrast, a non-incremental Sieve would have
        to pre-allocate a 90gb+ list before it could even start doing any work.
        it also manages to be almost as fast a non-incremental implementation.

        paraphrased from: http://code.activestate.com/recipes/117119/
    """
    composite_factors = {}
    for i in xrange(2, N+1):
        if i not in composite_factors:
            # `i` has no factors before itself so it must be prime
            yield i
            # mark the prime's square.
            # skipping ahead to the square is a common Sieve omptimization.
            # all composites between this prime and its square will be marked
            # by smaller primes, but the square cannot be marked by anything
            # but this prime because the square only has one factor: the prime.
            # we could mark `i*2` instead, but that's only useful if you're
            # trying to generate all unique prime factors for each composite.
            # either way, this basically 'seeds' the prime in the factors dict
            # so it can propagate to larger composites in the else-block below.
            composite_factors[i * i] = [i]
        else:
            # propagate factors to their next multiples
            for p in composite_factors[i]:
                composite_factors.setdefault(i + p, []).append(p)
            # we're done with this one
            del composite_factors[i]

def prime_factors(N):
    for prime in _sieve(N):
        if N % prime == 0:
            return sorted([prime] + prime_factors(N / prime))
    return []
