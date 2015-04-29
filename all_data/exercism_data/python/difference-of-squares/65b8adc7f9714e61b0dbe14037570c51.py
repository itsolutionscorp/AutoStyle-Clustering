def difference(x):
    return square_of_sum(x) - sum_of_squares(x)

def square_of_sum(x):
    sum = reduce(lambda x,y: x+y, [x for x in range(1, x+1)])
    return sum*sum

def sum_of_squares(x):
    return reduce(lambda x,y: x+y, [x*x for x in range(1, x+1)])
