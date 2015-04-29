import re

class Phone(object):
	def __init__(self, number):
		self.number = re.sub('[^0-9]+', '', number)
		if len(self.number) > 10:
			self.number = re.sub('^1', '', number, count=1)
		if len(self.number) != 10:
			self.number = '0000000000'

	def area_code(self):
		return self.number[0:3]

	def pretty(self):
		return "({}) {}-{}".format(self.area_code(), self.number[3:6], self.number[6:])
