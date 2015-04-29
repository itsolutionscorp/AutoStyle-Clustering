def square_of_sum(num):
    result = 0
    for n in range(1, num+1):
        result += n
    return result ** 2

def sum_of_squares(num):
    result = 0
    for n in range(1, num+1):
        result += (n**2) 
    return result

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
