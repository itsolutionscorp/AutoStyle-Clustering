#
#
#

def is_leap_year(year):

    def is_evenly_divisible_by_4(year):
        if year % 4 == 0:
            return year
        else:
            return False

    def is_evenly_divisible_by_100(year):
        if year % 100 == 0:
            return year
        else:
            return False

    def is_evenly_divisble_by_400(year):
        if year % 400 == 0:
            return year
        else:
            return False

    if is_evenly_divisible_by_4(year) and not is_evenly_divisible_by_100(year):
        return True
    elif is_evenly_divisible_by_4(year) and is_evenly_divisible_by_100(year) and is_evenly_divisble_by_400(year):
        return True
    else:
        return False
