def difference(number):
    diff = square_of_sum(number) - sum_of_squares(number)
    return diff

def sum_of_squares(number):
    all_nums = range(1, number + 1)
    sum_working = 0

    for this_num in all_nums:
        sum_working = sum_working + (this_num ** 2)
        
    return sum_working

def square_of_sum(number):
    all_nums = range(1, number + 1)
    sum_working = 0
    
    for this_num in all_nums:
        sum_working = sum_working + this_num
        
    return sum_working**2
