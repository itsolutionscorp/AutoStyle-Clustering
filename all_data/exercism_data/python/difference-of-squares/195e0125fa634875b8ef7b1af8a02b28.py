def square_of_sum(num):
    return sum([i for i in range(num + 1)]) ** 2

def sum_of_squares(num):
    return sum([i ** 2 for i in range(num + 1)])

def difference(num):
    return abs(sum_of_squares(num) - square_of_sum(num))
