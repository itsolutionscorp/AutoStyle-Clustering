planet_years = {'earth': 1.0, 'mercury':0.2408467,'venus': 0.61519726,'mars': 1.8808158,'jupiter': 11.862615,'saturn': 29.447498,'uranus': 84.016846,'neptune': 164.79132}
YEAR_ON_EARTH_IN_SECONDS = 60*60*24*365.25

class SpaceAge():

	def __init__(self, seconds):
		self.seconds = seconds
		self._years = 1.0*self.seconds/YEAR_ON_EARTH_IN_SECONDS

	def __getattr__(self, attr):
		if not attr.startswith('on_') or attr[3:] not in planet_years:
			raise AttributeError(attr)
		return lambda: round(self._years/planet_years[attr[3:]], 2)
