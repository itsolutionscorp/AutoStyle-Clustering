def square_of_sum(num):
    val = 0
    for i in range(1, num+1):
        val += i
    return val ** 2


def sum_of_squares(num):
    val = 0
    for i in range(1, num+1):
        val += i**2
    return val


def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
