def square_of_sum(in_number):
    numbers_sum = 0
    numbers = range(1, in_number+1)
    for num in numbers:
        numbers_sum += num
    return numbers_sum**2


def sum_of_squares(in_number):
    squares_sum = 0
    numbers = range(1, in_number+1)
    for num in numbers:
    	squares_sum += num**2
    return squares_sum


def difference(in_number):
    sum_of_the_square = sum_of_squares(in_number)
    square_of_the_sum = square_of_sum(in_number)

    return square_of_the_sum - sum_of_the_square
