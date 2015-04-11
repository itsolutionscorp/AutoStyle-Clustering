def is_leap_year(year):
    a = False
    if year%4==0:
        if year%100!=0 or year%400==0:
            a = True
    return a
