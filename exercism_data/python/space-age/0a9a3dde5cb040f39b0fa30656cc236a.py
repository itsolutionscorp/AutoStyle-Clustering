class SpaceAge(object):

	def __init__(self,seconds):
		self.seconds = seconds

	def age(self, multiple):
		remaining = self.seconds
		years = 0
		while remaining >= 3600*24*365.25*multiple:
			years += 1
			remaining -= 3600*24*365.25*multiple
		years += float(remaining)/3600/24/365.25/multiple
		return round(years,2)

	def on_earth(self):
		return self.age(1)

	def on_mercury(self):
		return self.age(0.2408467)

	def on_venus(self):
		return self.age(0.61519726)

	def on_mars(self):
		return self.age(1.8808158)

	def on_jupiter(self):
		return self.age(11.862615)

	def on_saturn(self):
		return self.age(29.447498)

	def on_uranus(self):
		return self.age(84.016846)

	def on_neptune(self):
		return self.age(164.79132)
