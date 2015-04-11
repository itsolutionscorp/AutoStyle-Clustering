# difference of two numbers
def difference(num):
    return abs(square_of_sum(num) - sum_of_squares(num))

# square of sum from 1 to num
def  sum_of_squares(num):
    sum = 0
    for x in range(1, num + 1):
        sum += x**2
    return sum

# sum of squares from 1 to sum
def square_of_sum(num):
    sum = 0
    for x in range(1, num + 1):
        sum += x
    return sum ** 2
