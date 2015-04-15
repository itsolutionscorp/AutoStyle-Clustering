import datetime
weekdays_dict = {
	"Monday" : 0,
	"Tuesday" : 1,
	"Wednesday" : 2,
	"Thursday" : 3,
	"Friday" : 4,
	"Saturday" : 5,
	"Sunday" : 6
}

nth_week = {
	"1st" : 1,
	"2nd" : 8,
	"3rd" : 15,
	"4th" : 22,
	"last" : 25,
	"teenth" : 13
}

def meetup_day(year, month, weekday, nth_weekday):
	for day in range(nth_week[nth_weekday],
	 nth_week[nth_weekday] + 7):
		if weekdays_dict[weekday] == datetime.date(
			year, month, day).weekday():
			return datetime.date(year, month, day)
