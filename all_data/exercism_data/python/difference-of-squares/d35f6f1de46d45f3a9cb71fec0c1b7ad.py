def square_of_sum(num):
    # Generate a lis of natural numbers
    list_numbers = []
    for i in range(1, num+1):
        list_numbers.append(i)

    return (sum(list_numbers))**2  # Return sum of squares


def sum_of_squares(num):
    # Generate a lis of natural numbers
    list_numbers = []
    for i in range(1, num+1):
        list_numbers.append(i)

    return sum([n ** 2 for n in list_numbers])  # Return sum of squares


def difference(num):
    # Return de Difference
    return square_of_sum(num) - sum_of_squares(num)
