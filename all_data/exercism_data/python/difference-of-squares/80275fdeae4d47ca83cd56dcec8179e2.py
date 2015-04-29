def difference(x):
    return  square_of_sum(x) - sum_of_squares(x)

def square_of_sum(x):
    result = 0
    i = 1
    while i <= x:
        result += i
        i += 1
    return result**2

def sum_of_squares(x):
    result = 0
    i = 1
    while i <= x:
        square = i**2
        result += square
        i += 1
    return result
