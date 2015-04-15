# leap year True if
# divisible by 4
# divisible by 400
# 
# leap year false if
# divisible by 100

def is_leap_year(given_year):
    try:
        given_year = int(given_year)
        if given_year % 100 == 0:
            return given_year % 400 == 0

        return given_year % 4 == 0 
    except:
        print('Could convert given year into integer')
        return False
