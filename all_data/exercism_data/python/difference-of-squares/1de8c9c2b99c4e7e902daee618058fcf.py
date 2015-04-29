def square_of_sum(src):
    sum = 0
    for i in range(src):
        sum += (i+1)
    sum = sum**2
    return sum

def sum_of_squares(src):
    sum = 0
    for i in range(src):
        sum += (i+1)**2
    return sum

def difference(src):
    return square_of_sum(src) - sum_of_squares(src)
