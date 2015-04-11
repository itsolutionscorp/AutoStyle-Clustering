def sieve(n):
    numbers = []
    for i in range(2, n+1):
        numbers.append(i)
    for i in range(2, n+1):
        for t in range(2, n+1):
            t *= i
            if t in numbers:
                numbers.remove(t)
    return numbers
