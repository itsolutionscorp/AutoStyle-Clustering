def sieve(limit):
    numbers = list(range(3,limit,2))
    for prime in numbers:
        for multiplier in reversed(range(2,limit)):
            try:
                numbers.remove(prime*multiplier)
            except ValueError:
                pass
    return [2]+numbers
