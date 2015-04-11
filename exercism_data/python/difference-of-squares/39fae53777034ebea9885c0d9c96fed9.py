def square_of_sum(n):    
    return sum(range(1, n + 1)) ** 2


def sum_of_squares(n):
    return sum([num**2 for num in range(1, n + 1)])


def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
