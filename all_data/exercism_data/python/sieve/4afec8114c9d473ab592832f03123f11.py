def sieve(num):
    return [prime for prime in prime_generator(num)]


def prime_generator(num):
    nonprimes = []
    for x in range(2, num+1):
        if x not in nonprimes:
            nonprimes.extend(range(x, num+1, x))
            yield x
