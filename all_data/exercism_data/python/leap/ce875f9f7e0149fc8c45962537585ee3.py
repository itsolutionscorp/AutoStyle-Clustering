def is_leap_year(year):
    is_century = year%100==0
    return year%(400 if is_century else 4)==0
