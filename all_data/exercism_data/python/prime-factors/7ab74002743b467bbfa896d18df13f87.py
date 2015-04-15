#!/usr/bin/env python2
# -*- coding: utf-8 -*-

"""
Sorry I'm not a primes expert.
Interesting links are welcome.
Copies of others' code are not.
"""

def is_prime(num, ext):
    tmp = num/2
    if tmp % 2 == 0:
        tmp -= 1
    if tmp % 5 == 0:
        tmp -= 2
    gen = (x for x in xrange(11, tmp, 2))
    for x in gen:
        if x % 5 == 0 or x in ext:
            continue
        elif num % x == 0:
            return False
    return True

def prime_factors(num):

    tmp = num # A modifyable temporary variable.
    pf = [] # Prime factors, list to return
    pn = tmp/2 # We only ever need 1/2 the search space if factorable.
    pr = [2, 3, 5, 7] # Prime Primes (seed)

    # Reduce the number to odd.
    # Handle primary primes.
    for x in pr:
        while tmp > 1 and tmp % x == 0:
            pf.append(x)
            tmp /= x

    # You shall not pass.
    if tmp == 1:
        return pf

    # If it's divisible by 5, reduce to the next odd number.
    if pn % 2 == 0:
        pn -= 1
    if pn > 5 and pn % 5 == 0:
        pn -= 2

    # Yielded generator
    arf = (x for x in xrange(11, pn, 2))

    # Iterate
    for x in arf:
        if x % 5 == 0:
            continue
        else:
            if tmp % x == 0:
                np = 0
                for y in pr:
                    if x % y == 0:
                        np = 1
                        break
                # This must be done.
                # AFTER checking if tmp is divisible by x
                # Checking before is expontentially slower.
                if np == 1 or not is_prime(x, pr):
                    continue
                # Minor optimization (irrelevant).
                pr.append(x)
                # Should only happen once the larger the # gets.
                while tmp % x == 0:
                    pf.append(x)
                    tmp /= x
            if tmp == 1:
                break
    return pf

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
