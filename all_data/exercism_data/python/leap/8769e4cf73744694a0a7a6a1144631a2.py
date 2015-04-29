
def is_leap_year(year):
    year = int(year)
    if ((year % 400) == 0):
        return True
    elif ((year % 100) == 0):
        return False
    elif ((year % 4) == 0):
        return True

    #Year isn't divisible by 4
    return False

if __name__ == '__main__':
    import sys
    year = sys.argv[1]
    print is_leap_year(year)
