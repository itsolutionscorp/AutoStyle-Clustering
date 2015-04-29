

def difference(upper_limit: int) -> int:
    return square_of_sum(upper_limit) - sum_of_squares(upper_limit)


def square_of_sum(upper_limit: int) -> int:
    return sum(range(upper_limit + 1))**2


def sum_of_squares(upper_limit: int) -> int:
    return sum([i**2 for i in range(upper_limit + 1)])
