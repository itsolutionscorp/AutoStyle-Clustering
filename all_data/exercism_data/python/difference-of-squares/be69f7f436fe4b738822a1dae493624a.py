def sum_of_squares(num):
    s = sum([x**2 for x in range(1, num + 1)])
    return s

def square_of_sum(num):
    s = sum(range(1, num + 1))
    return s**2

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
