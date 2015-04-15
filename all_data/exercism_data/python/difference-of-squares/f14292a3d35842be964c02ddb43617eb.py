def square_of_sum(num):
    mice = 0
    rats = 0
    square_of_sum = 0
    for i in range(1, num + 1):
        mice += 1
        rats = rats + mice
        square_of_sum = rats**2
    return square_of_sum

def sum_of_squares(num):
    tigers = 0
    lions = 0
    sum_of_squares = 0
    for i in range(1, num + 1):
        tigers += 1
        tigsums = tigers**2
        sum_of_squares = sum_of_squares + tigsums
    return sum_of_squares

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
