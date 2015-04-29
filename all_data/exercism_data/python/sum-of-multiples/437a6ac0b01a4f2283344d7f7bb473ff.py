def sum_of_multiples(limit, factor_list=None):
    if factor_list == None:
        factor_list = [3,5]
    mult_sum = 0
    for i in range(1, limit):
        for f in factor_list:
            if f == 0:
                continue
            if i % f == 0:
                mult_sum += i
                break
    return mult_sum
