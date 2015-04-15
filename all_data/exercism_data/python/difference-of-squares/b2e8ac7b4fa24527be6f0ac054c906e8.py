# Pavkazzz.


def difference(numb):
    return square_of_sum(numb) - sum_of_squares(numb)

def square_of_sum(numb):
    sum = 0
    for i in range(numb+1):
        sum += i
    return sum ** 2

def sum_of_squares(numb):
    sum = 0
    for i in range(numb+1):
        sum += i ** 2
    return sum
