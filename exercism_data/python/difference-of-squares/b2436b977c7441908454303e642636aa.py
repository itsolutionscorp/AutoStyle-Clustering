'''ex squares, sums and differences'''

def difference(val):
    return square_of_sum(val) - sum_of_squares(val)

def square_of_sum(val):
    sum = 0
    for num in range(val + 1):
        sum += num
    return sum ** 2


def sum_of_squares(val):
    sum = 0
    for num in range(val + 1):
        sum += num ** 2
    return sum
