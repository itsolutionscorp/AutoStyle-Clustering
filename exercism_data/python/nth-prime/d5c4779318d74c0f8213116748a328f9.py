import math

def is_prime(n):
    if n % 2 == 0 and n > 2:
        return False

    for i in range(3, int(math.sqrt(n)) + 1, 2):
        if n % i == 0:
            return False
    return True

def nth_prime(nth):
    n = 2
    prime_numbers = []
    while nth > len(prime_numbers):
        if is_prime(n):
            prime_numbers.append(n)
            n += 1
            next
        else:
            n += 1
    return prime_numbers[nth-1]
