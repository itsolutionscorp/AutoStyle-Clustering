#------------------------------
# Name:        Mcface3000
# Purpose:      Hahahaha!  Wooo
#------------------------------

def difference(variable):
    return square_of_sum(variable) - sum_of_squares(variable)
def square_of_sum(square):
    count = 1
    increment = 2

    while increment < square + 1:
        count = count + increment
        increment += 1
    return count ** 2
def sum_of_squares(variable):
    square = 2
    increment = 1
    count = 0
    while increment != variable + 1:
        count = count + increment**square
        increment += 1
    return count
