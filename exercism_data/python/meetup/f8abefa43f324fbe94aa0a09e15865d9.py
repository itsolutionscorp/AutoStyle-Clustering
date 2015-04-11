# meetup_day(2013, 5, 'Monday', 'teenth'))
from datetime import date, timedelta
import calendar


class Meetup:
	
	weekdays = {
		'Monday': 		0,
		'Tuesday': 		1,
		'Wednesday': 	2,
		'Thursday': 	3,
		'Friday': 		4,
		'Saturday': 	5,
		'Sunday': 		6
	}
	

	def __init__(self, year, month, weekday, day):

		self.year = year
		self.month = month
		self.weekday = self.weekdays[weekday]
		self.day = day
		self.meetup_date = None


	def _get_next_day(self):
		"""Loops through the days in the month"""

		c = calendar.Calendar(calendar.MONDAY)
		for day in c.itermonthdays(self.year, self.month): 
			if not day == 0:
				yield day


	def _ordinal(self, count):
		
		for day in self._get_next_day():
			self.meetup_date = date(self.year, self.month, day)
			if self.meetup_date.weekday() == self.weekday:
				count -= 1
				if count == 0:
					break

		return self.meetup_date


	def _teenth(self):

		for day in self._get_next_day(): 
			if day in [13, 14, 15, 16, 17, 18, 19]:
				adate = date(self.year, self.month, day)
				if adate.weekday() == self.weekday:
					self.meetup_date = adate
					break


		return self.meetup_date
		
		
	def _last(self):

		for day in self._get_next_day(): 
			adate = date(self.year, self.month, day)
			if adate.weekday() == self.weekday:
				self.meetup_date = adate

		return self.meetup_date


	def meetup_day(self):
		
		if self.day == '1st':
			self._ordinal(1)
		elif self.day == '2nd':
			self._ordinal(2)
		elif self.day == '3rd':
			self._ordinal(3)
		elif self.day == '4th':
			self._ordinal(4)
		elif self.day == 'teenth':
			self._teenth()
		elif self.day == 'last':
			self._last()
		else:
			pass	
	
		return self.meetup_date



def meetup_day(year, month, weekday, day):

	meetup = Meetup(year, month, weekday, day)

	d = meetup.meetup_day()

	return d
