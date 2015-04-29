def square_of_sum(limit):
    sum_upto = _sum_upto(limit)
    return sum_upto * sum_upto

def sum_of_squares(limit):
    sum = 0
    for number in range(limit+1):
        sum += (number * number)
    return sum

def difference(limit):
    return square_of_sum(limit) - sum_of_squares(limit)

def _sum_upto(limit):
    sum = 0
    for number in range(limit+1):
        sum +=number
    return sum
