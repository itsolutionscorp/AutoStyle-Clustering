def difference(num):
    return square_of_sum(num)-sum_of_squares(num)

def square_of_sum(num):
    sum=0
    for x in range(num+1):
        sum+=x
    return sum**2

def sum_of_squares(num):
    sum=0
    for x in range(num+1):
        sum+=x**2
    return sum
