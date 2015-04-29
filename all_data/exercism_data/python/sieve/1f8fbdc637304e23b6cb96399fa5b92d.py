__author__ = 'Cedric Zhuang'


def sieve(n):
    prime = [2]
    for i in xrange(3, n + 1, 2):
        for j in xrange(2, i/2):
            if i % j == 0:
                break
        else:
            prime.append(i)
    return prime


