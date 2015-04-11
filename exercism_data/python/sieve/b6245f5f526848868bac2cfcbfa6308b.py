def sieve(limit):
    return iterate(1, range(2, limit + 1))


def iterate(start, working_range):
    if start == len(working_range):
        return working_range

    current_prime = working_range[start - 1]

    i = start
    while i < len(working_range):
        if working_range[i] % current_prime:
            i += 1
        else:
            working_range.pop(i)

    return iterate(start + 1, working_range)
