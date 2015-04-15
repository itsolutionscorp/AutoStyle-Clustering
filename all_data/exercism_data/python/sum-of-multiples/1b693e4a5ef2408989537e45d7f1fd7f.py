def sum_of_multiples(number, factors=[3,5]):
    if 0 in factors:
        # zero will never contribute to sums, and causes problems with division
        factors.remove(0)
    return sum(x for x in range(number) if is_multiple(x, factors))


def is_multiple(number, factors):
    for x in factors:
        if number % x == 0:
            return True
    return False
