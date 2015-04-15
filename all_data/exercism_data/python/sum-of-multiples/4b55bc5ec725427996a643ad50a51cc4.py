def sum_of_multiples(number, factors=None):
    factors = factors or [3, 5]

    # find all our factors and put them in a set so there are no duplicates
    return sum({n for factor in factors if factor for n in range(factor, number, factor)})
