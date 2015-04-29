def sum_of_multiples(num, multiples=[3, 5]):
    sets = [frozenset(range(0, num, x)) for x in multiples if x]
    if not sets:
        return 0
    return sum(reduce(frozenset.union, sets))
