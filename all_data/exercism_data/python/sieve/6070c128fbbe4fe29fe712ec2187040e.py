import math

def sieve(limit):
    numbers = range(3, limit, 2)
    for i in xrange(int(math.sqrt(len(numbers))) + 1):
        p = numbers[i]
        for n in xrange(2 * p, limit, p):
            try:
                numbers.remove(n)
            except:
                pass
    return [2] + numbers
