#Test for leap_years

def is_leap_year(year):
    if year % 4 == 0 and year % 100 !=0:
	return True
    if year % 100 == 0 and year % 400 == 0:
        return True 
    else:
        return False
