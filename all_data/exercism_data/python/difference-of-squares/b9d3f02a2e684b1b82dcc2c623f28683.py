def difference(number: int) -> int:
    return square_of_sum(number) - sum_of_squares(number)


def square_of_sum(number: int) -> int:
    return sum([x for x in range(number + 1)]) ** 2


def sum_of_squares(number: int) -> int:
    return sum([x ** 2 for x in range(number + 1)])
