def difference(num):
    return square_of_sum(num) - sum_of_squares(num)

def square_of_sum(num):
    # Square of the first n numbers added together
    numList = xrange(1,num+1)
    return ((numList[0] + numList[-1]) * len(numList) / 2) ** 2

def sum_of_squares(num):
    # Finds the sum of the first n squares
    return sum(c ** 2 for c in xrange(num+1))

#eof
