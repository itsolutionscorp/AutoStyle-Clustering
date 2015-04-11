def difference(end_num):
    return abs(sum_of_squares(end_num) - square_of_sum(end_num))

def square_of_sum(end_num):
    return sum([i for i in range(1, end_num + 1)]) ** 2

def sum_of_squares(end_num):
    return sum([i**2 for i in range(1, end_num + 1)])
