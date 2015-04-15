def square_of_sum(num):
    return (sum(a for a in range(num+1))**2)
def sum_of_squares(max):
    return sum(b*b for b in range(max+1))
def difference(diff):
    return abs(sum_of_squares(diff) - square_of_sum(diff))
