def nth_prime(target):
    i, k = 0, 0
    while k < target:
        if is_prime(i):
            k += 1
        i += 1
    return i - 1

def is_prime(n):
    if n in xrange(2, 3):
        return True
    if n == 1 or n % 2 == 0:
        return False

    i = 3
    while i < (n * 0.5 + 1):
        if n % i == 0:
            return False
        i += 2
    return True
