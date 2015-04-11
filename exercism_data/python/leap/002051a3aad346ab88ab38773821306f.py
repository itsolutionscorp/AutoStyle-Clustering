def by_n(y, n):
    return y % n == 0

def is_leap_year(year):
    if not by_n(year, 100):
        return by_n(year, 4)
    return by_n(year, 400)

if __name__ == '__main__':
    print is_leap_year(2020)
    print is_leap_year(2000)
    print is_leap_year(1900)
