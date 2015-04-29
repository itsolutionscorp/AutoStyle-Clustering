

def sieve(limit):
    numbers = {number: False for number in range(2, limit + 1)}
    for prime, mark in numbers.items():
        if not mark:
            end_range = limit // prime + 1
            for multiplier in range(prime**2, end_range):
                numbers[multiplier * prime] = True

    return [number for number, mark in numbers.items() if not mark]
