def is_leap_year(n):
    return (n % 400 == 0) or (n % 4 == 0 and n % 100 != 0)
