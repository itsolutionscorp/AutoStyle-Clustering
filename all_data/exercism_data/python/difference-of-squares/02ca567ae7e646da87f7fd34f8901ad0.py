def square_of_sum(input):
    # mathematical calculation for sum of a set of numbers where x is the max number is:
    # x * (x + 1) / 2
    return (input * (input + 1) / 2) ** 2
    
def sum_of_squares(input):
    # mathematical calculation for sum of the squares of a set of numbers where x is the max number is:
    # x * (x + 1) * (2 * x + 1) / 6
    return input * (input + 1) * (2 * input + 1) / 6

def difference(input):
    return square_of_sum(input) - sum_of_squares(input)
