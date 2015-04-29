def difference(number: int) -> int:
    return square_of_sum(number) - sum_of_squares(number)


def square_of_sum(number: int) -> int:
    return sum(range(1, number + 1)) ** 2


def sum_of_squares(number: int) -> int:
    return sum(map(lambda x: x ** 2, range(1, number + 1)))
