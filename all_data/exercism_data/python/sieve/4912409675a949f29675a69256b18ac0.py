def sieve(limit):
    numbers = range(2, limit)
    i = 0
    while i < len(numbers):
        p = numbers[i]
        n = 2 * p
        while n < limit:
            if n in numbers:
                numbers.remove(n)
            n += p
        i += 1
    return numbers
