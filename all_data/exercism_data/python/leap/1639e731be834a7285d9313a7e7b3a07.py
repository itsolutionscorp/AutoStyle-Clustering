def fizzbuzz(y):
    if y % 4 == 0:
        if y % 100 == 0:
            if y % 400 == 0:
                return True
            return False
        return True
    return False

def is_leap_year(y):
    return fizzbuzz(y)
