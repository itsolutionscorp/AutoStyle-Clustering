def sum_of_multiples(num, multiples=[3, 5]):
    sets = [frozenset(range(x, num, x)) for x in multiples if x]
    if not sets:
        return 0
    return sum(reduce(frozenset.union, sets))


def _sum_of_multiples(nums, multiples=[3, 5]):
    nums = range(0, nums)
    filtered = filter(lambda x: any([x % y == 0 for y in multiples if y]), nums)
    return sum(filtered)
