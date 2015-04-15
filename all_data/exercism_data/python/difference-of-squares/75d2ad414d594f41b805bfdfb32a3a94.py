def sum_of_squares(num):
    #from http://en.wikipedia.org/wiki/Square_pyramidal_number
    return (2*num**3 + 3*num**2 + num)/6

def square_of_sum(num):
    return (num*(num+1)/2)**2
    
def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
