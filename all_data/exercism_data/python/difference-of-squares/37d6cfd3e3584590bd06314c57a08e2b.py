def difference(num):
    return abs(sum_of_squares(num) - square_of_sum(num))

def square_of_sum(num):
    return sum(range(1,num+1)) ** 2

def sum_of_squares(num):
    return sum([x**2 for x in range(1,num+1)])
