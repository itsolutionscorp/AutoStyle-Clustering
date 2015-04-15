#difference_of_squares.py




def difference(d):
    return square_of_sum(d)-sum_of_squares(d)

def square_of_sum(d):
    return sum([x for x in range(1,d+1)])**2

def sum_of_squares(d):
    return sum([x**2 for x in range(1,d+1)])
