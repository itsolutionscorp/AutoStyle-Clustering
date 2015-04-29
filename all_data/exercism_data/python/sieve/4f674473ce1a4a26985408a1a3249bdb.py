import math

def sieve(n):
        primemarks = [False, False] + [True] * n

        for i in xrange(2, n+1):
            if primemarks[i]:
                for j in xrange(i**2, n+1, i):
                    primemarks[j] = False

        return [x for x, tf in enumerate(primemarks) if tf and x <= n]
