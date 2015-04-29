from datetime import date, timedelta
from calendar import monthrange

def meetup_day(year, month, day, notation):
	"""
	Input: 	Integer year and month, String day (Sunday through Saturday)
			and String notation (first - fifth, last, 'teenth').
	Output: Corresponding date in Date format.
	"""

	mDate 	= date(year, month, 1)

	if notation.startswith('l'):												# Last x of the month.
		mDate = mDate.replace(day=(monthrange(mDate.year, mDate.month)[1]))		# Calculate the last day of the month.
		while (mDate.strftime("%A") != day):
			mDate = mDate - timedelta(days=1)									# Check backwards.
		return mDate
	elif notation.startswith('t'):												# First 'teenth' of the month.
		mDate = mDate.replace(day=13)											# Start from the 13th.
		while (mDate.strftime("%A") != day):
			mDate = mDate + timedelta(days=1)									# Check forwards.
		return mDate
	else:
		mDate = mDate.replace(day=((int(notation[0]) - 1) * 7) + 1)				# Starts us off on the first possible day.
		while (mDate.strftime("%A") != day):
			mDate = mDate + timedelta(days=1)									# Check forwards.
		if mDate.month != month:												# If we're suddenly out of the month
			assert MeetupDayException											# raise the exception.
		else:
			return mDate
