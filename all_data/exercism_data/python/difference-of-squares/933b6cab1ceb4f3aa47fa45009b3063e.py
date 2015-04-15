def square_of_sum(number):
    square = sum(range(0,number+1))**2
    return square
    
    
    
def sum_of_squares(number):
    sum = 0
    for i in range(0,number+1):
        sum += i**2
    
    return sum
    
def difference(number):
    difference = square_of_sum(number) - sum_of_squares(number)
    return difference
