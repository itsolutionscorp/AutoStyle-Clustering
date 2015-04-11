def square_of_sum(num):
    return (sum(range(1, num+1)))**2  # Return sum of squares


def sum_of_squares(num):
    return sum([n ** 2 for n in range(1, num+1)])  # Return sum of squares


def difference(num):
    # Return de Difference
    return square_of_sum(num) - sum_of_squares(num)
