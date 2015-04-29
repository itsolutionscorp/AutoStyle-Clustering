# represents age relative to each of the 8 planets in our solar system
class SpaceAge(object):
	_earth_year=31557600
	_other_years={'Mercury':0.2408467, 'Venus':0.61519726, 'Earth':1, 'Mars':1.8808158, 'Jupiter':11.862615, 'Saturn':29.447498, 'Uranus':84.016846, 'Neptune':164.79132}

	def __init__(self,secs):
		self._seconds=secs

	# exact age on Earth
	def earth_age(self):
		return self._seconds/SpaceAge._earth_year
		
	def on_planet(self,planet):
		return round(self.earth_age()/SpaceAge._other_years[planet],2)

	# age on Earth rounded to two decimal places
	def on_earth(self):
		return self.on_planet('Earth')

	@property
	def seconds(self):
		return self._seconds

	def on_mercury(self):
		return self.on_planet('Mercury')

	def on_venus(self):
		return self.on_planet('Venus')

	def on_mars(self):
		return self.on_planet('Mars')

	def on_jupiter(self):
		return self.on_planet('Jupiter')

	def on_saturn(self):
		return self.on_planet('Saturn')

	def on_uranus(self):
		return self.on_planet('Uranus')

	def on_neptune(self):
		return self.on_planet('Neptune')
