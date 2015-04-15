def square_of_sum(i):
    return reduce(lambda acc, n: acc+n, range(1,i+1))**2

def sum_of_squares(i):
    return reduce(lambda acc, n: acc+n**2, range(1,i+1))

def difference(i):
    return square_of_sum(i) - sum_of_squares(i)
