from datetime import date, timedelta

'''
	This class represents the Gigasecond object
	Given a date, it determines the Gigasecond birthday
'''
class Gigasecond(object):
	gigaseconds = long(1e9)
	max_seconds_per_day = 86400

	def __init__(self, date):
		self.date = self.get_giga_birthday(date)

	def get_giga_birthday(self, date):
		tdelta = timedelta(days=self.seconds_to_days())
		return (date + tdelta)

	def seconds_to_days(self):
		return int(self.gigaseconds/self.max_seconds_per_day)
