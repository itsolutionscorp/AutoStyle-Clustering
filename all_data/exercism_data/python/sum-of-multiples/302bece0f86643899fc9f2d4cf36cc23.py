def sum_of_multiples(number, factors=[3, 5]):
    return sum({n for factor in factors if factor for n in range(factor, number, factor)})
