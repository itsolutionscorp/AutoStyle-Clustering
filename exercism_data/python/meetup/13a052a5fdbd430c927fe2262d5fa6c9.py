from datetime import date, timedelta

def meetup_day(year,month,day_of_week,selected_day):
	days_of_week = {
		'Monday': 0,
		'Tuesday': 1,
		'Wednesday': 2,
		'Thursday': 3,
		'Friday': 4,
		'Saturday': 5,
		'Sunday': 6
	}
	start_days = {
		'1st': 1,
		'2nd': 8,
		'3rd': 15,
		'4th': 22,
		'teenth': 13,
		'last': 0
	}

	if start_days[selected_day] == 0:
		init_day = date(year,month+1,1)
		weekday_delta = -((7-(days_of_week[day_of_week] - init_day.weekday()))%7)

	else:
		init_day = date(year,month,start_days[selected_day])
		weekday_delta = (7 + days_of_week[day_of_week] - init_day.weekday()) % 7 

	return init_day + timedelta(days=weekday_delta)

	
	
