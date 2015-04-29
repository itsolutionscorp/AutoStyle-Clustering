def square_of_sum(value):
    sum = 0
    for i in range(1, (value + 1)):
        sum += i
    return (sum ** 2)

def sum_of_squares(value):
    sum = 0
    for i in range(1, (value + 1)):
        sum += (i ** 2)
    return sum

def difference(value):
    return ( square_of_sum(value) - sum_of_squares(value) )
