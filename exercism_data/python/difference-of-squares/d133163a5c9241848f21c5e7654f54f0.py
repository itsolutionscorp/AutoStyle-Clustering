def square_of_sum(num):
    ret_num=0
    for i in range(num):
        ret_num+=i+1
    return ret_num**2

def sum_of_squares(num):
    ret_num=0
    for i in range(num):
        ret_num+= (i+1)**2
    return ret_num

def difference(num):
    return abs(sum_of_squares(num)-square_of_sum(num))
