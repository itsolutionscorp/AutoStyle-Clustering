import math
def sieve(maxPrime):
    primeList = [True] * (maxPrime - 1)
    for i in range(0, int(math.sqrt(maxPrime))):
        if primeList[i] == True:
            for j in range(2 * (i+2), maxPrime + 1, i+2):
                primeList[j-2] = False

    return[x+2 for i,x in zip(primeList, range(len(primeList))) if i == True]
