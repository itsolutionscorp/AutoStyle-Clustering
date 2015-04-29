def sieve(number):
    number_range = list(range(2, number + 1))
    marked_numbers = []

    for n in number_range:
        for x in range(n, number + 1, n):
            if x not in marked_numbers and x != n:
                marked_numbers.append(x)

    primes = list(set(number_range).difference(marked_numbers))

    return primes
