def sum_of_multiples(limit, factors=[3,5]):
    limit_too_small = limit < factors[0]
    invalid_factors = factors == [0]

    if limit_too_small or invalid_factors:
        return 0
    multiples = [range(number, 
                       limit,
                       number) for number in factors if number > 0]
    sum = (lambda x, y: x + y)
    reduced_multiples = set(reduce(sum, multiples)) # set is used here to remove common factors
    return reduce(sum, reduced_multiples)
