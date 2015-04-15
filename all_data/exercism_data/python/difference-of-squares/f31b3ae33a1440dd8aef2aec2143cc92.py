def difference(number):
    return square_of_sum(number)-sum_of_squares(number)
    
def square_of_sum(number):
    total=0
    for i in range(0, number+1):
        total+=i
    return total**2

def sum_of_squares(number):
    total=0
    for i in range(0,number+1):
        total+=i**2
    return total
