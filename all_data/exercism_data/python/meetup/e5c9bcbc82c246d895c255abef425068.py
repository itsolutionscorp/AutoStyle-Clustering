import datetime

possible_dates = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, '5th': 4, 'last': -1} 

def meetup_day(year, month, day, which):
	dates_on_day = { 'Monday': [], 'Tuesday': [], 'Wednesday': [], 'Thursday': [], 
			'Friday': [], 'Saturday': [], 'Sunday': []}
	
	# Get the day of the week for each date, starting with 1st.

	date = datetime.date(year, month, 1)
	while date.month == month:
		day_of_week = date.strftime('%A')
		dates_on_day[day_of_week].append(date.day)
		date = date + datetime.timedelta(days = 1)	
	
	if which in possible_dates:
		index = possible_dates[which]
		day_date = dates_on_day[day][index]

	if which == 'teenth':
		day_date = [d for d in dates_on_day[day] if 12 < d < 20][0]

	return datetime.date(year, month, day_date)
