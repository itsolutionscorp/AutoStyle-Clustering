from math import sqrt

def nth_prime(n):
    primelist, next = [2], 3

    # No optimizations being done.
    while len(primelist) < n:
        if all(next%prime for prime in primelist):
            primelist.append(next)
        next += 1

    return primelist[n-1]
