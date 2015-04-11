def sieve(limit):
    primes = []
    marks = [0b1] * (limit + 1)
    marks[0] = marks[1] = 0b0
    i = 2

    while i ** 2 <= limit:
        primes.append(i)
        mark = 2 * i

        while mark <= limit:
            marks[mark] = 0b0
            mark += i

        i += 1

        while not marks[i]:
            i += 1

    while i <= limit:
        if marks[i]:
            primes.append(i)

        i += 1

    return primes
