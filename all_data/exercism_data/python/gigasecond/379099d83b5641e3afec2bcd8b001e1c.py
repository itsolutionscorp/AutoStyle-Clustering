# a program that will calculate the date that someone turned
# or will celebrate their 1Gs anniversary. 1 gigasecond == one billion seconds

from datetime import date
from datetime import timedelta

class Gigasecond(object):
	def __init__(self, begin):
		self.begin = begin 

	@property 
	def date(self):
		return self.begin + timedelta(seconds=1000000000)
