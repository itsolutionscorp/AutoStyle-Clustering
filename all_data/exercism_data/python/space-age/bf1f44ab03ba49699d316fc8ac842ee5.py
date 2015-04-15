'''space_age.py
	created 11 Oct 2014
	by @jestuber '''

class SpaceAge(object):
	"""given an age in seconds, calculates how old someone is in terms of a given planet's solar years."""
	def __init__(self, sec):
		super(SpaceAge, self).__init__()
		self.seconds = sec

	def on_earth(self):
		return self.format_2dec(self.seconds / 31557600.)
	def on_mercury(self):
		return self.format_2dec(self.seconds / 31557600. / 0.2408467)
	def on_venus(self):
		return self.format_2dec(self.seconds / 31557600. / 0.61519726)
	def on_mars(self):
		return self.format_2dec(self.seconds / 31557600. / 1.8808158)
	def on_jupiter(self):
		return self.format_2dec(self.seconds / 31557600. / 11.862615)
	def on_saturn(self):
		return self.format_2dec(self.seconds / 31557600. / 29.447498)
	def on_uranus(self):
		return self.format_2dec(self.seconds / 31557600. / 84.016846)
	def on_neptune(self):
		return self.format_2dec(self.seconds / 31557600. / 164.79132)

	def format_2dec(self,num):
		return float("{0:.2f}".format(num))
