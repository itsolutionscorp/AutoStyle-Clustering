

def square_of_sum(max_num):
    thesum = 0
    for i in range(0, max_num + 1):
        thesum  = thesum + i
    return thesum ** 2

def difference(max_num):
    return square_of_sum(max_num) - sum_of_squares(max_num)

def sum_of_squares(max_num):
    thesum = 0
    for i in range(0, max_num + 1):
        thesum = thesum + i**2
    return thesum
