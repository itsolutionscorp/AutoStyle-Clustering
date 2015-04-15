from math import sqrt

def sieve(n):
    integers = [True for i in range(n + 1)]
    integers[0], integers[1] = False, False
    
    for i in range(2, int(sqrt(n)) + 1):
        if integers[i] == True:
            for j in range(i * i, n + 1, i):
                integers[j] = False

    primes =[]

    for i in range(len(integers)):
        if integers[i] == True:
            primes.append(i)

    return primes
