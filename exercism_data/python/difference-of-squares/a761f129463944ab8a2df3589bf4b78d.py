def difference(num):
    return square_of_sum(num)-sum_of_squares(num)



def square_of_sum(num):
    return sum(range(1,num+1))**2


def sum_of_squares(num):
    return sum((e**2 for e in range(1,num+1)))
