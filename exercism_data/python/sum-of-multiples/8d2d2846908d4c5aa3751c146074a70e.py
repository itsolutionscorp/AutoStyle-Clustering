def sum_of_multiples(number, factors=None):
    if factors is None:
        factors = [3, 5]

    # find all numbers divisible by our factors and put them in a set so there are no duplicates
    sum_list = set([n for n in range(1, number) for y in factors if y and n % y == 0])

    # sum the list
    multiple_sum = 0
    for n in sum_list:
        multiple_sum += n

    return multiple_sum
