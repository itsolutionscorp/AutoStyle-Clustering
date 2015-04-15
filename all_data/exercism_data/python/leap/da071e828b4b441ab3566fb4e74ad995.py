def is_leap_year(num):
    if not num%4:
        if not num%100 and num%400:
            return False
        return True
    else:
        return False
