def sieve(number):
    number_range = list(range(2, number + 1))

    # remove every x that is a multiple of n (except when x == n)
    [[number_range.remove(x) for x in number_range if x % n == 0 and x != n] for n in number_range]

    return number_range
