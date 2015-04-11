def difference(value):
    return square_of_sum(value) - sum_of_squares(value)

def square_of_sum(value):
    return (sum(range(value + 1))) ** 2
    
def sum_of_squares(value):
    sum = 0
    for i in range(0,value+1):
        sum += i**2
    return sum
