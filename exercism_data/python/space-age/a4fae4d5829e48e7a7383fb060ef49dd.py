planets = { 	'earth' : 1, 'mercury' : 0.2408467, 'venus' : 0.61519726,
		'mars' : 1.8808158, 'jupiter' : 11.862615, 'saturn' : 29.447498,
		'uranus' : 84.016846, 'neptune' : 164.79132   }

one_year = 3600 * 24 * 365.25

class SpaceAge(object):
	def __init__(self, time_in_seconds):
		self.seconds = time_in_seconds
		self.yr = time_in_seconds / (3600 * 24 * 365.25)
	def on_earth(self):	return round(self.yr / planets['earth'], 2)
	def on_mercury(self):	return round(self.yr / planets['mercury'], 2)
	def on_venus(self):	return round(self.yr / planets['venus'], 2)
	def on_mars(self):	return round(self.yr / planets['mars'], 2)
	def on_jupiter(self):	return round(self.yr / planets['jupiter'], 2)
	def on_saturn(self):	return round(self.yr / planets['saturn'], 2)
	def on_uranus(self):	return round(self.yr / planets['uranus'], 2)
	def on_neptune(self):	return round(self.yr / planets['neptune'], 2)
