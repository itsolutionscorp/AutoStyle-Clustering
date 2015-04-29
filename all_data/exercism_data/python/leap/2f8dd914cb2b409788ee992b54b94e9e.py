def is_leap_year(input):
    if input % 4 == 0:
        if input % 100 == 0:
            if input % 400 == 0:
                return True
            return False
        return True
    
