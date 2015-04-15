def difference(number):
    return square_of_sum(number) - sum_of_squares(number)

def square_of_sum(number):
    return sum(x+1 for x in range(number))**2

def sum_of_squares(number):
    return sum((x+1)**2 for x in range(number))
