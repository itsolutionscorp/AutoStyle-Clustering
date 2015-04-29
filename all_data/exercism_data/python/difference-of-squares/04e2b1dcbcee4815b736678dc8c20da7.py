def difference(x):
    return square_of_sum(x) - sum_of_squares(x)

def square_of_sum(x):
    return sum_up(x) ** 2

def sum_of_squares(x):
    return sum_up(x) * (2 * x + 1) / 3

def sum_up(x):
    return ((1 + x) * x) / 2
