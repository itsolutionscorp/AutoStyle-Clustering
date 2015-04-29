def is_leap_year(year):
    def is_divisible_by(number):
        return year % number == 0

    if is_divisible_by(4):
        if is_divisible_by(100) and not is_divisible_by(400):
            return False
        else:
            return True
    else:
        return False
