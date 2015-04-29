# Difference Of Squares

def square_of_sum(square_sum_num):
    return sum(range(1, square_sum_num + 1)) ** 2

def sum_of_squares(sum_squares_num):
    return sum([x ** 2 for x in range(1, sum_squares_num + 1)])

def difference(num):
    return abs(square_of_sum(num) - sum_of_squares(num))
