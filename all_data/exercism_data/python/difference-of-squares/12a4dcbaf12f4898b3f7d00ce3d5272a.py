def difference(num):    
    return square_of_sum(num) - sum_of_squares(num) 

def sum_of_squares(num):
    return sum([num**2 for num in range(1,num+1)])

def square_of_sum(num):
    return sum(range(1,num+1))**2
