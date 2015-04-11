def difference(num):
    return square_of_sum(num) - sum_of_squares(num)


def square_of_sum(num):
    return sum(range(1, num + 1))**2


def sum_of_squares(num):
    if num == 1:
        return 1
    else:
        return num**2 + sum_of_squares(num-1)
