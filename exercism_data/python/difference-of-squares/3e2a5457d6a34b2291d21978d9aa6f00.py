def difference(num):    
    return square_of_sum(num) - sum_of_squares(num) 

def sum_of_squares(num):
    def summation(x,y): return x+y**2
    return reduce(add, range(1,num+1), 0)

def square_of_sum(num):
    def summation(x,y): return x+y
    return reduce(add, range(1,num+1))**2
