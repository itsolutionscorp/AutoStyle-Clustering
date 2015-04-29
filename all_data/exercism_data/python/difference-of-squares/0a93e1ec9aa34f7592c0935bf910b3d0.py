def difference(n):
    return square_of_sum(n) - sum_of_squares(n)


def square_of_sum(n):
    sequence = range(1, n + 1)
    return sum(sequence)**2


def sum_of_squares(n):
    sequence = range(1, n + 1)
    squares = [n**2 for n in sequence]
    return sum(squares)
