def square_of_sum(num):
    the_sum = lambda x: x if x < 2 else x + the_sum(x-1)
    return the_sum(num)**2

def sum_of_squares(num):
    the_sum = lambda x: x if x < 2 else x**2 + the_sum(x-1)
    return the_sum(num)

def difference(num):
    return abs(sum_of_squares(num) - square_of_sum(num))
