from operator import sub


def square_of_sum(num) -> int:
    return pow(sum(range(1, num+1)), 2)


def sum_of_squares(num) -> int:
    return sum(map(lambda x: x**2, range(1, num+1)))


def difference(num) -> int:
    return sub(square_of_sum(num), sum_of_squares(num))
