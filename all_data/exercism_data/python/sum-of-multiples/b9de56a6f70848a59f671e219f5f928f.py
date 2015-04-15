

def sum_of_multiples(limit, factor_list = [3, 5]):

    ## That's going to be a little bit backward:
    ## Take the integers below the limit and filter
    ## out anything that is a multiple of the
    ## factors in the list.
    ## Sum up the remaining numbers and substract the
    ## the result from the sum of all integers below the
    ## limit.

    int_list = list(range(limit))

    for div in factor_list:
        if div == 0:
            continue
        int_list = list(filter(lambda n : bool(n % div), int_list))

    return sum(range(limit), 0) - sum(int_list, 0)


