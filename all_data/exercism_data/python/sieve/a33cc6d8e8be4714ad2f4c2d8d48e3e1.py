__author__ = 'jimblackler'


def sieve(n):
    array = [False] * (n + 1)
    results = []
    for i in xrange(2, n + 1):
        if array[i]:
            continue
        results.append(i)
        for j in xrange(i + i, n + 1, i):
            array[j] = True
    return results
