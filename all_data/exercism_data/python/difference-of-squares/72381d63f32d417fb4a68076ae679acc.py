
def square_of_sum(limit):
    return sum(range(limit + 1))**2

def sum_of_squares(limit):
    return sum(n*n for n in range(limit + 1))

def difference(limit):
    return square_of_sum(limit) - sum_of_squares(limit)
