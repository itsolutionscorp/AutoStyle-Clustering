def square_of_sum(input):
    x = 0
    for i in range(1, input +1):
        x += i
    return x ** 2
    
def sum_of_squares(input):
    x = 0
    for i in range(1, input +1):
        x += i ** 2
    return x

def difference(input):
    return square_of_sum(input) - sum_of_squares(input)
