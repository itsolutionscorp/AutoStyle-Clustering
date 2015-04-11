from datetime import timedelta

class Gigasecond:
	def __init__(self, d):
		delta = timedelta(seconds=1000000000)
		self.date = d+delta
