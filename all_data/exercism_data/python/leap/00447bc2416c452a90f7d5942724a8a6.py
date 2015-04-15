def is_leap_year(whichYear):
#
# Most years are not leap years.
#
	leap = False
#
# If the year divides evenly by four...
#
	if not whichYear % 4:
#
# ...then it MIGHT be a leap year...
#
		leap = True
#
# ...but not if it also divides evenly by 100...
#
		if not whichYear % 100:
			leap = False
#
# ...UNLESS it ALSO divides evenly by 400!
#
			if not whichYear % 400:
				leap = True
	return leap
