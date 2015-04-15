def square_of_sum(num):
    nums_to_sum = sum(range(1, num+1))
    return nums_to_sum**2

def sum_of_squares(num):
    current_sum_of_squares = (n**2 for n in range(1, num+1))
    return sum(current_sum_of_squares)

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
