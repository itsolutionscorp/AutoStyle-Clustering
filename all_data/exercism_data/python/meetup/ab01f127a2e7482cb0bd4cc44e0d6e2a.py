from datetime import date
import calendar

DAY_LOOKUP = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5,'Sunday': 6}
IDX_LOOKUP = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': -1, 'teenth': -2}

def rotate(matrix):
	return list(zip(*matrix))

def get_day(ds, idx):
	if (idx == -2):  # teenth...
		return next(x for x in ds if ((x >= 13) and (x <= 19)))
	else:
		return ds[idx]

def meetup_day(y, m, day, index):
	cal = rotate(calendar.Calendar(0).monthdayscalendar(y, m))
	ds  = [x for x in cal[DAY_LOOKUP[day]] if (x != 0)]
	return date(y, m, get_day(ds, IDX_LOOKUP[index]))
