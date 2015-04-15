def sum_of_multiples(limit, factors=[3, 5]):
    return sum([number for number in range(1, limit) if any(number % factor == 0 for factor in factors if factor != 0)])
