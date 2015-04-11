def sum_of_squares(num):
    squares = [x**2 for x in range(num + 1)]
    return sum(squares) 
    
def square_of_sum(num):
    numbers = [y for y in range(num + 1)]
    return sum(numbers) ** 2
    
def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
