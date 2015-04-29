def square_of_sum(number):
    s = sum([i for i in range(1, number + 1)])
    return s ** 2
    
def sum_of_squares(number):
    s = sum([i ** 2 for i in range(1, number + 1)])
    return s
    
def difference(number):
    return square_of_sum(number) - sum_of_squares(number)
