def square_of_sum(num):
    return (num ** 4 + 2 * num ** 3 + num ** 2) / 4

def sum_of_squares(num):
    return (2 * num ** 3 + 3 * num ** 2 + num) / 6

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)