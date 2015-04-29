def is_leap_year(input):
    if input % 400 == 0: return True
    elif input % 100 == 0: return False
    elif input % 4 == 0: return True
    else: return False
