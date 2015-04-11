def sum_of_multiples(number, factors=None):
    if factors is None:
        factors = [3, 5]

    # find all numbers divisible by our factors and put them in a list
    sum_list = [n for n in range(1, number) for y in factors if y and n % y == 0]

    # remove duplicates resulting from numbers being divisible by more than one factor
    [sum_list.remove(n) for n in sum_list if sum_list.count(n) > 1]

    # sum the list
    multiple_sum = 0
    for n in sum_list:
        multiple_sum += n

    return multiple_sum
