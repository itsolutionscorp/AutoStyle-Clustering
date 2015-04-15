#year.py

def is_leap_year(year):
    state = False
    if not year%4:
        if (year%100) or (not year%400):
            state = True
    return state
