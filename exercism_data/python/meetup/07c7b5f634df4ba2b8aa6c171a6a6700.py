
def meetup_day(year,month,day,number):
	
	dict = {
	'Monday':0,
	'Tuesday':1,
	'Wednesday':2,
	'Thursday':3,
	'Friday':4,
	'Saturday':5,
	'Sunday':6,
	'1st':0,
	'2nd':1,
	'3rd':2,
	'4th':3,
	'last':4
	}

	from datetime import date
	from datetime import timedelta #import the date method

	daynum = 1 #initialize the first day of the month

	testdate = date(year,month,daynum) #create a test date

	loop = 0

	while loop == 0:
		if testdate.weekday() == dict[day]:

			if number == "teenth":
					while testdate.day < 13:
						testdate = testdate + timedelta(7)
					return testdate

			dmod = 7 *dict[number]

			testdate = testdate + timedelta(dmod)

			loop = 1

		else:
			testdate = testdate + timedelta(1)
			loop = 0

	return testdate
