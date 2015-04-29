def square_of_sum(input):
    local_sum = 0
    for i in range(input + 1):
        local_sum += i
    return local_sum ** 2
    
def sum_of_squares(input):
    local_squares = 0
    for i in range(input + 1):
        local_squares += i ** 2
    return local_squares
    
def difference(input):
    return square_of_sum(input) - sum_of_squares(input)

    
