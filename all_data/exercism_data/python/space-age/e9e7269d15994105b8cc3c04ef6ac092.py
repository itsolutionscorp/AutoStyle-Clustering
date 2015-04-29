base = 31557600.0
ratios = {'earth': 1.0, 'mercury': 0.2408467, 'venus': 0.61519726, 'mars': 1.8808158,
'jupiter': 11.862615, 'saturn': 29.447498, 'uranus': 84.016846, 'neptune': 164.79132 }

class SpaceAge(object):
	def __init__(self, seconds):
		self.seconds = seconds
		
	def __getattr__(self, name):
		s = name.split('_')
		if len(s) == 2 and s[1] in ratios:
			return lambda: round(self.seconds/base/ratios[s[1]], 2)
		else:
			raise AttributeError
