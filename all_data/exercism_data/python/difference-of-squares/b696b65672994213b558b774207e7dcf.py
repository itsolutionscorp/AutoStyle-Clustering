def difference(limit: int) -> int:
    return square_of_sum(limit) - sum_of_squares(limit)


def square_of_sum(limit: int) -> int:
    return sum(range(1, limit+1)) ** 2


def sum_of_squares(limit: int) -> int:
    return sum(x**2 for x in range(1, limit+1))
