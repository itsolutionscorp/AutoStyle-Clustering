def is_leap_year(year):
    if isinstance(year, str):
        if year.isdigit():
            year = int(year)
    if isinstance(year, int):
        d4 = year % 4
        d16 = year % 16
        d25 = year % 25
        return not d4 and (d25 or (not d25 and not d16))
    else:
        return "Not a number."
    
