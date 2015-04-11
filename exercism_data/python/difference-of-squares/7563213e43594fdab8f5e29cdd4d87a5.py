# (num ** 2 + num) / 2 === sum(range(num + 1))
def square_of_sum(num):
    return ((num ** 2 + num) / 2) ** 2

def sum_of_squares(num):
    return sum([i ** 2 for i in range(num + 1)])

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
