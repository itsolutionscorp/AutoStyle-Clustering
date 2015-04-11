def difference(number):
    return square_of_sum(number)-sum_of_squares(number)
    
def square_of_sum(number):
    return ((number**2 + number)/2)**2

def sum_of_squares(number):
    return (2*number**3 + 3*number**2 + number)/6
