def difference(number):
    return abs(square_of_sum(number) - sum_of_squares(number))

def square_of_sum(number):
    return (number * (number + 1) / 2)**2

def sum_of_squares(number):
    return number * (number + 1) * (2*number + 1) / 6
