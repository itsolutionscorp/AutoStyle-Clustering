def difference(num):
        return square_of_sum(num) - sum_of_squares(num)

def square_of_sum(num):
    n = 0
    for i in range(num+1):
        n +=i

    return n**2

def sum_of_squares(num):
    n = 0
    for i in range(num+1):
        n+=i**2

    return n
