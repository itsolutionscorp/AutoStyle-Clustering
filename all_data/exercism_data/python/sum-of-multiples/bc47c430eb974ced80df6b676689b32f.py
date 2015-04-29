def sum_of_multiples(n, base = [3, 5]):
    is_multiple_of_base = lambda x : any([y != 0 and x % y == 0 for y in base])
    return sum([x for x in range(0, n) if is_multiple_of_base(x)])
