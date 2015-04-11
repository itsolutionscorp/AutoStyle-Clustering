def difference(num):
    return (square_of_sum(num) - sum_of_squares(num))

def square_of_sum(num):
    return int(num*(num+1)/2)**2

def sum_of_squares(num):
    return sum([y**2 for y in range(1,num+1)])
