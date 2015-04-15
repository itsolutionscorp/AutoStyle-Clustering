def square_of_sum(input):
    return sum(range(1, input +1)) ** 2
    
def sum_of_squares(input):
    return sum(x ** 2 for x in range(1, input +1))

def difference(input):
    return square_of_sum(input) - sum_of_squares(input)
