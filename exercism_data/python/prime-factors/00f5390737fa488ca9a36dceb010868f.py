__author__ = 'tracyrohlin'

from utils import isprime

import math

"""def prime_factors(number):
    prime_list =[]
    for x in xrange(2, number+1):
        while number % x == 0:
            if isprime(x):
                prime_list.append(x)
                number = number / x

    return prime_list

print prime_factors(93819012551)"""

def factors(n):
    factors_list = []

    if n == 0:
        factors_list = []
    else:
        for i in range(1, int(math.floor(n ** (1/2)) + 1)):
            print "in for loop"
            if n % i == 0:
                factors_list.append(i)
                factors_list.append(n // i)
    return factors_list

print factors(8)
number = int(math.floor(8 ** (1/2)) + 1)
print number
