#!/usr/bin/python
# exercism python 12: sieve

def sieve(limit):
    candidates = range(2,limit)
    for number in xrange(2,limit):
        if not number in candidates:
            continue
        divisor = limit/number
        for factor in xrange(2,divisor+1):
            if number*factor in candidates:
                candidates.remove(number*factor)
    return candidates
        
