def division_test(y): #returns lambda function to test if x divides y
    return lambda x: y % x == 0

def is_leap_year(year):
    is_divisible_by = division_test(year)
    if is_divisible_by(400):
        return True
    if is_divisible_by(100):
        return False
    return is_divisible_by(4)
