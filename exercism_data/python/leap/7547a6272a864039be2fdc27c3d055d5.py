# leap year exercise

def is_leap_year(year):
    if year % 4 == 0:               # leap years are divisible by 4
        if year % 100 == 0:         # except when divisible by 100
            return year % 400 == 0  # unless also divisible by 400
        return True
    return False
