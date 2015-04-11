
def difference(e):
    return square_of_sum(e) - sum_of_squares(e)

def square_of_sum(e):
    return sum(range(1,e+1))**2

def sum_of_squares(e):
    return sum(list(map(lambda x: x**2, range(1, e+1))))
