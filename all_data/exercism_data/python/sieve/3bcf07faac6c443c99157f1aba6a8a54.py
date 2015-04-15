__author__ = 'tracyrohlin'

from utils import isprime

def sieve(n):
    prime_list = [2]
    for x in range(3, n+1):
        if isprime(x) == True:
            prime_list.append(x)

    return prime_list
