def is_leap_year(year):
    # retun statement short circuits to false if year isn't divisible by 4
    # and it short circuits to false if year is divisible by 100
    # but it returns true if year is divisible by 4 unless it is divisible
    # by 100 and not 400
    return not year % 4 \
        and year % 100 \
        or not year % 400
