# find leap year 

def is_leap_year(year):
    try:
        if year % 4 == 0 and year %100 != 0 or year % 400 == 0:
            return True
        else:
            return False
            

    except ValueError:
        print("this is not a year")
        return False
