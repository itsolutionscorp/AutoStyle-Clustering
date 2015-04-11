def is_leap_year(date):

##Simplest to define when it isn't and default to True

    #Not divisible by 4	
    if date%4 != 0:
	return False

    #Divisible by 100 but not 400
    elif (date%100 == 0) and (date%400 != 0):
	return False

    #Defaults to True otherwise
    else:
	return True
