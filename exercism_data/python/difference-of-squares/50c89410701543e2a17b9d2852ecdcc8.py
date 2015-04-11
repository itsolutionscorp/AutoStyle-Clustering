def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
def square_of_sum(num):
    num_list = range(1,num+1)
    return sum(num_list)**2
def sum_of_squares(num):
    num_list = range(1,num+1)
    return sum([x**2 for x in num_list])
