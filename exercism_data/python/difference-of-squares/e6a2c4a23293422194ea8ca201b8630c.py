def square_of_sum(num):
    total = 0
    while num >= 1:
        total += num
        num -=  1
    return total*total


def sum_of_squares(num):
    total = 0
    while num >= 1:
        total += num*num
        num -=  1
    return total

def difference(num):
    return abs(sum_of_squares(num)-square_of_sum(num))
