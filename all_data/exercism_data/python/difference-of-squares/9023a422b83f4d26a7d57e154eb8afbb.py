def sum_of_squares(number,summation = 0):
    
    if number == 0:
        return summation
    else:
        summation += number**2
        return sum_of_squares(number-1,summation)
        
def square_of_sum(number, summation = 0):
    
    if number == 0:
        return summation**2
    else:
        summation += number
        return square_of_sum(number-1,summation)

def difference(number):
    
    return square_of_sum(number) - sum_of_squares(number)
