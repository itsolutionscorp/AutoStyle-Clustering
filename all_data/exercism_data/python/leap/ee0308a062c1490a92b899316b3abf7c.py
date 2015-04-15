#-*- coding: utf-8 -*-

def is_leap_year(year):
    leap = False
    # Modulus operation returns 0, hence the 'not'.
    # Using a comparison to 0 would probably look better.
    # Nested if's avoid computing all three calculations with all inputs.
    if not year % 4:
        leap = True
        if not year % 100:
            leap = False
            if not year % 400:
                leap = True
    return leap
