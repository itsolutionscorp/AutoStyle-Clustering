__author__ = 'Cedric Zhuang'


# from betegelse's solution
# http://exercism.io/submissions/c99db68b3b98371a54f9cf4a
def sieve(n):
    all_num = [0, 0] + [1] * n
    prime = []
    i = 2
    while i <= n:
        prime.append(i)
        for j in xrange(i, n + 1, i):
            all_num[j] = 0
        i = all_num.index(1, i)
    return prime


