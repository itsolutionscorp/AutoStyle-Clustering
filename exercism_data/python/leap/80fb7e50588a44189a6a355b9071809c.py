def is_leap_year(num):
    if not num % 4 and (num % 100 or not num % 400):
        return True
    return False
