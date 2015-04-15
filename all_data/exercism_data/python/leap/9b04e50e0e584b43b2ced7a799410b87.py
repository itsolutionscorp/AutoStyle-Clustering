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
            if given_year % 400 == 0:
                return True
            else:
                return False
	# Placing Last because if placed as first condition then 1900
        # returns true. Could refactor by making this first then changing
        # test for 100 under it and then under 100 check if divisble by 400
        if given_year % 4 == 0:
            return True
        else:
            return False  
    except:
        print('Could convert given year into integer')
        return False
