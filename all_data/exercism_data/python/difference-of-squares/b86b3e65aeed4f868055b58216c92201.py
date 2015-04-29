def sum_of_squares(num):
    sumSq = 0
    for n in xrange(1, num+1):
        sumSq += n**2
    return sumSq

def square_of_sum(num):
    total = 0
    for n in xrange(1, num+1):
        total += n
    return total**2

def difference(num):
    return abs(sum_of_squares(num) - square_of_sum(num))
