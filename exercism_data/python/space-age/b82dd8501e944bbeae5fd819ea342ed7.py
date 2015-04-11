# represents age relative to each of the 8 planets in our solar system
class SpaceAge(object):
	_earth_year=31557600
	_other_years={'Mercury':0.2408467, 'Venus':0.61519726, 'Mars':1.8808158, 'Jupiter':11.862615, 'Saturn':29.447498, 'Uranus':84.016846, 'Neptune':164.79132}

	def __init__(self,secs):
		self._seconds=secs

	# exact age on Earth
	def earth_age(self):
		return self._seconds/SpaceAge._earth_year

	# age on Earth rounded to two decimal places
	def on_earth(self):
		return round(self.earth_age(),2)

	@property
	def seconds(self):
		return self._seconds

	def on_mercury(self):
		return round(self.earth_age()/SpaceAge._other_years['Mercury'],2)

	def on_venus(self):
		return round(self.earth_age()/SpaceAge._other_years['Venus'],2)
		
	def on_mars(self):
		return round(self.earth_age()/SpaceAge._other_years['Mars'],2)
		
	def on_jupiter(self):
		return round(self.earth_age()/SpaceAge._other_years['Jupiter'],2)
		
	def on_saturn(self):
		return round(self.earth_age()/SpaceAge._other_years['Saturn'],2)
	
	def on_uranus(self):
		return round(self.earth_age()/SpaceAge._other_years['Uranus'],2)
		
	def on_neptune(self):
		return round(self.earth_age()/SpaceAge._other_years['Neptune'],2)
