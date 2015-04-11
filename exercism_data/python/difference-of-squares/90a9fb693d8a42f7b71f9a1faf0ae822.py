def make_squares(num):
    iterations = num

    while iterations > 0:
        iterations -= 1
        yield num ** 2
        num -= 1


def difference(num):
    return square_of_sum(num) - sum_of_squares(num)


def square_of_sum(num):
    return sum([x for x in xrange(num + 1)]) ** 2


def sum_of_squares(num):
    return sum(make_squares(num))
