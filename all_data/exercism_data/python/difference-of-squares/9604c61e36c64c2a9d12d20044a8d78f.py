RANGE = 100
POWER = 2
# initializing to avoid needless repetition of calculations.
sum_of_squares_ls = []
square_of_sum_ls = []
prev_1 = prev_2 = 0

for i in range(1, RANGE + 1):

    sum_of_squares_ls.append((i ** POWER) + prev_1)
    square_of_sum_ls.append((i + prev_2) ** POWER)

    prev_1 = sum_of_squares_ls[i - 1]
    prev_2 += i

#======================================================


def square_of_sum(num):
    if num <= 0:
        return 0
    return square_of_sum_ls[num - 1]


def sum_of_squares(num):
    if num <= 0:
        return 0
    return sum_of_squares_ls[num - 1]


def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
