def is_leap_year(year):
    a=False
    if year%4==0:
        a=True
	if year%100==0:
	    a=False
	    if year%400==0:
		a=True
    return a	
