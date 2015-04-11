def square_of_sum(number):
    sumn = 0
    while number > 0:
        sumn += number
        number -= 1
    sumn **= 2
    return sumn

def sum_of_squares(number):
    sumn = 0
    while number > 0:
        sumn += number ** 2
        number -= 1
    return sumn

def difference(number):
    return square_of_sum(number) - sum_of_squares(number)
