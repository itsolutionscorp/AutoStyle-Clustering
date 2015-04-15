def difference(number):
    return square_of_sum(number) - sum_of_squares(number)


def sum_up(number):
    if number == 0:
        return 0
    else:
        return number + sum_up(number - 1)


def square_of_sum(number):
    return sum_up(number) ** 2


def sum_of_squares(number):
    if number == 0:
        return 0
    else:
        return (number ** 2) + sum_of_squares(number - 1)
