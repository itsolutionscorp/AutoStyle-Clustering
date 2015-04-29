def is_leap_year(a_year):
    if a_year % 4:
        return False
    elif not a_year % 100 and a_year % 400:
        return False
    else:
        return True

if __name__ == '__main__':
    year = int(input('input year: '))
    print(is_leap_year(year))
