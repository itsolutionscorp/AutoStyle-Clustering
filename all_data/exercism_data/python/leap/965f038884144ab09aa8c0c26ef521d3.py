def by_4(y):
    return y % 4 == 0

def by_100(y):
    return y % 100 == 0

def by_400(y):
    return y % 400 == 0

def is_leap_year(year):
    if not by_100(year):
        return by_4(year)
    return by_400(year)

if __name__ == '__main__':
    print is_leap_year(2020)
    print is_leap_year(2000)
    print is_leap_year(1900)
