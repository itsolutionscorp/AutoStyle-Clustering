def square_of_sum(stop):
    return sum(i for i in range(1, stop + 1)) ** 2


def sum_of_squares(stop):
    return sum(i ** 2 for i in range(1, stop + 1))


def difference(stop):
    return square_of_sum(stop) - sum_of_squares(stop)
