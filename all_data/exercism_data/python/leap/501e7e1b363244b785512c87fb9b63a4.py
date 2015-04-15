#This could probably be refactored to be more readable
def is_leap_year(year=1900):
    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                return True
            return False
        return True
    return False
