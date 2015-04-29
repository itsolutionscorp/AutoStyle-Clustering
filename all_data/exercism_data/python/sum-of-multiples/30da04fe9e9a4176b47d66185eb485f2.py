def sum_of_multiples(number, factors=None):
    if factors is None:
        factors = [3, 5]

    # find all numbers divisible by our factors and put them in a set so there are no duplicates
    return sum({n for n in range(1, number) for y in factors if y and n % y == 0})
