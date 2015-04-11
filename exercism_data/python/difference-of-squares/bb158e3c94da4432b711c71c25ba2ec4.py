def square_of_sum(number):
    return sum(range(1, number + 1)) ** 2


def sum_of_squares(number):
    return sum([x ** 2 for x in range(1, number + 1)])


def difference(number):
    return square_of_sum(number) - sum_of_squares(number)
