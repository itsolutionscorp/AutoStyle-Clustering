def square_of_sum(number):
    numbers = range(number+1)
    return (sum(numbers))**2

def sum_of_squares(number):
    numbers = range(number+1)
    return sum([number**2 for number in numbers])

def difference(number):
    return square_of_sum(number) - sum_of_squares(number)
