def difference(x):
    return square_of_sum(x) - sum_of_squares(x)

def square_of_sum(x):
    return (x*(x+1)/2)**2

def sum_of_squares(x):
    squares_list = map(lambda x:x**2, range(x+1))
    return sum(squares_list)
