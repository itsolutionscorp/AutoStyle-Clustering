#!/usr/bin/python
from math import sqrt
def sieve(n):
    A = dict(zip(range(2, n+1), [True]*n))
    for i in A.keys()[:int(sqrt(n))-1]:
        if A[i]:
            for j in A.keys()[(i**2)-2::i]:
                A[j] = False
    return [a[0] for a in A.items() if a[1]]
