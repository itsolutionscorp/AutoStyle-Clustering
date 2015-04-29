#!/usr/bin/python3
def is_leap_year(year):
    leap = False
    # needs to be a positive int
    try:
        if year >= 0:
            # mod 4
            if year % 4 == 0:
                leap = True
                if year % 100 == 0:
                    leap = False
                    if year % 400 == 0:
                        leap = True
        return leap
    except TypeError:
        print('Year must be an integer')

# eof
