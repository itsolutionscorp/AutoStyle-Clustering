from math import sqrt, ceil

def sieve(n):
    numbers = list(range(2,n))
    for i in range(2, ceil(sqrt(n))):
        j = 2
        while i * j < n:
            if i * j in numbers:
                numbers.remove(i * j)
            j += 1
    return numbers
