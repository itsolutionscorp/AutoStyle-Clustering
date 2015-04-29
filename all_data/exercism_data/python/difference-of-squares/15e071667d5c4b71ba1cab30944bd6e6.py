def square_of_sum(first):
    sum_first = sum(range(1, first+1))
    return sum_first**2
    
def sum_of_squares(first):
    square_first = [x**2 for x in range(1, first+1)]
    return sum(square_first)

def difference(first):

    return square_of_sum(first) - sum_of_squares(first)
