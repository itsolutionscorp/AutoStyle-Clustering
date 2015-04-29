def is_leap_year(year):

    return not ((year % 4) or (not (year % 100) and (year % 400)))

# A: % 4
# B: % 100
# C: % 400
# A + not(B).C
