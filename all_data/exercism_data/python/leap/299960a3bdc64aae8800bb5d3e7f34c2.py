def is_leap_year(year):
    is_leap = 'No'
    if (year % 4 ==0) and  (year % 100) != 0:
        is_leap = 'Yes'
    if (year % 400) == 0:
        is_leap = 'Yes'
    return is_leap
