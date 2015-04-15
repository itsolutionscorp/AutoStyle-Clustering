"""Skeleton for leap project. """

def is_leap_year(x):
    if x % 4 == 0:
        if x % 100 == 0:
            if x % 400 == 0:
                return True
            return False
        return True
    return False

print(is_leap_year(1900))
