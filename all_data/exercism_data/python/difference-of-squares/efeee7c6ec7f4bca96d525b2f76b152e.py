def sum_of_squares(the_range):
    start = 1
    the_sum = 0

    while start <= the_range:
        the_sum += start ** 2
        start += 1

    return the_sum


def square_of_sum(the_range):
    start = 1
    the_sum = 0

    while start <= the_range:
        the_sum += start
        start += 1

    return the_sum ** 2


def difference(the_range):
    sum_squared = square_of_sum(the_range)
    squares_summed = sum_of_squares(the_range)

    return abs(sum_squared - squares_summed)
