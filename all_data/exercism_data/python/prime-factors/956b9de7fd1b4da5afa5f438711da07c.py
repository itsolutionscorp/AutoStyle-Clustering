def prime_factors(n):
    if n == 1:
        return []
    first_factor = first(n)
    if first_factor:
        return [first_factor] + prime_factors(n/first_factor)
    else:
        return [n]


def first(n):
    for x in range(2, int(n ** .5 + 1)):
        if n % x == 0:
            return x
    return None
