def sum_of_multiples(number, factors=None):
    if factors is None:
        factors = [3, 5]

    # find all numbers divisible by our factors and put them in a list
    sum_list = [n for n in range(1, number) for y in factors if y and n % y == 0]

    # remove duplicates resulting from numbers being divisible by more than one factor
    # (sets can't have duplicates)
    sum_list = list(set(sum_list))

    # sum the list
    multiple_sum = 0
    for n in sum_list:
        multiple_sum += n

    return multiple_sum
