def is_leap_year(year):
	#Years that are divisible by 4 are leap years...
	#EXCEPT those also divisible by 100 and NOT by 400
	leap=False
	if year%4==0:
		leap=True
		if year%100==0 and year%400<>0:
			leap=False
	return leap
