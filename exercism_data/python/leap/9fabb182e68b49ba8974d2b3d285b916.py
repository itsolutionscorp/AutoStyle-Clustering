def is_leap_year(year):
    #leap years are divisible by 4
    #also centurial years have to be divisible by 400
    leapYear = not(bool(year % 4))
    if not year % 100:
        leapYear = leapYear and not(bool(year % 400))
    return leapYear

if __name__ == '__main__':
    is_leap_year(2003), is_leap_year(2002), is_leap_year(2001), is_leap_year(2000), is_leap_year(1900)
