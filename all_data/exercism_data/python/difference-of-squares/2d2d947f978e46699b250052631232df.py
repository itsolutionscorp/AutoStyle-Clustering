def square_of_sum(x):
    y = 0
    for z in range(x+1):
        y += z
    return y**2

def sum_of_squares(x):
    y = 0
    for z in range(x+1):
        y += z**2
    return y

def difference(x):
    return square_of_sum(x) - sum_of_squares(x)
