planets = { 	'earth' : 1, 'mercury' : 0.2408467, 'venus' : 0.61519726,
		'mars' : 1.8808158, 'jupiter' : 11.862615, 'saturn' : 29.447498,
		'uranus' : 84.016846, 'neptune' : 164.79132   }

one_year = 3600 * 24 * 365.25

class SpaceAge(object):
	def __init__(self, time_in_seconds):
		self.seconds = time_in_seconds

		for p, g in planets.iteritems():
			fun = self._fun_generator(g)
			setattr(self, 'on_{}'.format(p), fun)

	
	def _fun_generator(self, g):
		def fun():
			return round((self.seconds / one_year) / g, 2)
		return fun
