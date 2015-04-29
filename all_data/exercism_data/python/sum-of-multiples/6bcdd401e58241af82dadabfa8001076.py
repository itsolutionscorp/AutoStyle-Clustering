def sum_of_multiples(number, factor_list=[3, 5]):
    multiples_set = set()
    for factor in factor_list:
        if factor != 0:
            for x in range(factor, number, factor):
                multiples_set.add(x)
    return sum(multiples_set)
