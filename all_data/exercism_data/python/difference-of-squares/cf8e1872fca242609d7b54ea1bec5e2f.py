def square_of_sum(n):
    i = int(n)
    return (i*(i+1)/2)**2


def sum_of_squares(n):
    i = int(n)
    return i*(i+1)*(2*i+1)/6


def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
