def square_of_sum(num):
    return (sum(a for a in range(num+1))**2) #sums a (all numbers from 0 to num), then returns the square of that result
def sum_of_squares(num):
    return sum(a*a for a in range(num+1)) #returns the sum of the squares of a (all numbers from 0 to num)
def difference(num):
    return abs(sum_of_squares(num) - square_of_sum(num))
