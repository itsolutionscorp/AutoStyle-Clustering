

def difference(limit):
    return square_of_sum(limit)- sum_of_squares(limit)


def square_of_sum(limit):
    a = range(limit+1)
    b = sum(a)
    return b*b


def sum_of_squares(limit):
    squared = [a**2 for a in range(limit+1)]
    return sum(squared)

