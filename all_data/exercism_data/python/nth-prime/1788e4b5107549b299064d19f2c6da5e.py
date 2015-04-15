from utils import isprime
import math

def nth_prime(n):
    prime_list = []
    prime_list.append(2)

    for x in range(3, 1000000):
        if n == len(prime_list):
            break
        if isprime(x):
            prime_list.append(x)

    return prime_list[-1]

print nth_prime(6)
