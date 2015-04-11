def difference(number):
    # Compute and return the difference
    # between the square of the sum and
    # the sum of the squares of the given
    # number.
    return square_of_sum(number) - sum_of_squares(number)

def square_of_sum(count):
    # Turns out there's a built-in sum() function.
    # It saved me three lines of code.
    return sum(range(count+1))**2   
        
def sum_of_squares(count):
    # This turned out to be a great place
    # to use a list comprehension in combination
    # with the sum() function.
    return sum([x**2 for x in range(count+1)])
