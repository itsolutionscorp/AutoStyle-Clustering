#year.py
#for all your yearly inquiries


#is_leap_year: takes a year as input and returns true if a leap year and false otherwise
#a leap year is every year that is evenly divisible by 4
#except every year that is evenly divisible by 100
#unless the year is also evenly divisible by 400
def is_leap_year(year):
    if year % 400 == 0:
        return True
    elif year % 100 == 0:
        return False
    elif year % 4 == 0:
        return True
    else:
        return False
