def is_leap_year(date):

#Divisable by 4
    if date%4 != 0:
	return False
    elif (date%100 == 0) and (date%400 != 0):
	return False
    else:
	return True
