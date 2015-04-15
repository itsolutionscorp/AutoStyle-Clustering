def difference(number):
    diff = square_of_sum(number) - sum_of_squares(number)
    return diff

def sum_of_squares(number):
    all = range(1, number + 1)
    sum = 0

    for this_num in all:
        sum = sum + (this_num ** 2)
        
    return sum

def square_of_sum(number):
    all = range(1, number + 1)
    sum = 0
    
    for this_num in all:
        sum = sum + this_num
        
    return sum**2
