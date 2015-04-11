def square_of_sum(count):
    sum = 0
    for i in range(1, count + 1):
        sum += i
    return sum**2

def sum_of_squares(count):
    sum = 0
    for i in range(1, count + 1):
        sum += i**2
    return sum

def difference(count):
    return square_of_sum(count) - sum_of_squares(count)
