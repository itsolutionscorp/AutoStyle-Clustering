def difference(number):
    return square_of_sum(number) - sum_of_squares(number)

def square_of_sum(number):
    return sum(range(1, number+1))**2

def sum_of_squares(number):
    return sum(map(lambda x: x**2, range(1, number+1)))
