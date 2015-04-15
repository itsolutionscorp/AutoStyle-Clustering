__author__ = 'tracyrohlin'

from utils import isprime

def sieve(n):
    prime_list = []
    for x in range(2, n+1):
        if x == 2:
            prime_list.append(x)
        elif isprime(x) == True:
            prime_list.append(x)

    return prime_list

print sieve(1000)
