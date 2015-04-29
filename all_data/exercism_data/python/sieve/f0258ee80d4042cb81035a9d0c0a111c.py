def sieve(number):
    number_range = set(range(2, number + 1))
    marked_numbers = [x for n in number_range for x in range(n*n, number + 1, n)]

    return list(number_range.difference(marked_numbers))
