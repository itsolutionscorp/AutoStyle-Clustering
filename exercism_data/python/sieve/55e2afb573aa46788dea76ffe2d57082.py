import collections

def sieve(n):
    numbers = collections.OrderedDict({i: True for i in range(2, n + 1)})
    for prime in numbers.keys():
        if not numbers[prime]:
            continue
        for multiple in range(prime * 2, n + 1, prime):
            numbers[multiple] = False
    return [prime for prime in numbers if numbers[prime]]
