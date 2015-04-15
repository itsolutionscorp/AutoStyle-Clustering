def square_of_sum(num):
    t = 0
    for x in range(1,num+1):
        t += x
    return t**2
    
def sum_of_squares(num):
    t = 0
    for x in range(1,num + 1):
        t+= x**2
    return t

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
