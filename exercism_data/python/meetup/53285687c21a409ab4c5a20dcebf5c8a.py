from datetime import datetime, date;
import calendar;

def meetup_day(year, month, day_of_week, mod):
 # Days that end in 'teenth': 13, 14, 15, 16, 17, 18, 19
	week = {"Monday": 0, "Tuesday": 1, "Wednesday": 2, "Thursday": 3,
					"Friday": 4, "Saturday": 5, "Sunday": 6};
	week_inv = {0: "Monday", 1: "Tuesday", 2: "Wednesday", 3: "Thursday",
							4: "Friday", 5: "Saturday", 6: "Sunday"};

	week_day = week[day_of_week];

	if mod == "1st":
		for i in range(1, calendar.monthrange(year, month)[1] + 1):
			if date(year, month, i).weekday() == week[day_of_week]:
				return date(year, month, i);
	elif mod == "2nd":
		count = 0;
		for i in range(1, calendar.monthrange(year, month)[1] + 1):
			if date(year, month, i).weekday() == week[day_of_week]:
				if count == 1:
					return date(year, month, i);
				else:
					count += 1;
	elif mod == "3rd":
		count = 0;
		for i in range(1, calendar.monthrange(year, month)[1] + 1):
			if date(year, month, i).weekday() == week[day_of_week]:
				if count == 2:
					return date(year, month, i);
				else:
					count += 1;
	elif mod == "4th":
		count = 0;
		for i in range(1, calendar.monthrange(year, month)[1] + 1):
			if date(year, month, i).weekday() == week[day_of_week]:
				if count == 3:
					return date(year, month, i);
				else:
					count += 1;
	elif mod == "5th":
		count = 0;
		for i in range(1, calendar.monthrange(year, month)[1] + 1):
			if date(year, month, i).weekday() == week[day_of_week]:
				if count == 4:
					return date(year, month, i);
				else:
					count += 1;
	elif mod == "last":
		for i in range(calendar.monthrange(year, month)[1], 0, -1):
			if date(year, month, i).weekday() == week[day_of_week]:
				return date(year, month, i);
	elif mod == "teenth":
		for i in range(13, 20):
			if date(year, month, i).weekday() == week[day_of_week]:
				return date(year, month, i);

	raise ValueError("That combination doesn't exist.");
