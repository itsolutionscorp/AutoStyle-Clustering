def is_leap_year(year):
    isTrue = False

    if year%4 != 0:
        return False
    if year%100 == 0 and year%400 != 0:
        return False
    return True



if __name__ == '__main__':
    is_leap_year(1996);
