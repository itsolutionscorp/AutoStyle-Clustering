def div_by_4(year):
	return not year % 4 
def div_by_100(year):
	return not year % 100
def div_by_400(year):
	return not year % 400    

def is_leap_year(year):
    if div_by_100(year) and not div_by_400(year):
        return False
    elif div_by_4(year):
        return True
    else:
        return False
