def sum_of_multiples(limit, factor_list=[3,5]):
    multiples = []
    if 0 in factor_list:
        factor_list.remove(0)
    for i in range(1, limit):
        for f in factor_list:
            if i % f == 0:
                multiples.append(i)
                break
    if not multiples:
        return 0
    return reduce(lambda x,y: x+y, multiples)
