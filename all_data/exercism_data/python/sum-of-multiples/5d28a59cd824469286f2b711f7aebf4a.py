def sum_of_multiples(limit, factor_list=None):
    return sum([ (i if any([ (f != 0 and i % f == 0) for f in (factor_list or [3,5]) ]) else 0) for i in range(1,limit) ])
