def sieve(n):
    return [i for i in prime_generator(n)]

def prime_generator(n):
    preceding_primes = []
    for number in xrange(2,n):
        for prime in preceding_primes:
            # If number is a multiple of a preceding prime skip it
            if number % prime == 0:
                break
        else:
            # If number is not a multiple of any preceding prime add it to
            # the list and yield it
            preceding_primes.append(number)
            yield number
