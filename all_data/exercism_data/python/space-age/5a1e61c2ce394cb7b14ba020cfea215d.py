
class SpaceAge:
	def __init__(self, seconds):
		self.seconds = seconds
		self.earthSeconds = 31557600
		self.earthAge = float(seconds / float(self.earthSeconds))
		self.years = {'mercury':0.2408467, 'venus':0.61519726, 'earth': 1, 'mars': 1.8808158,
				'jupiter': 11.862615, 'saturn': 29.447498, 'uranus':84.016846, 'neptune':164.79132}
		
		for planet in self.years.keys():
			setattr(self, "on_" + str(planet), self.get_age(planet))
			
	def get_age(self, planet):
		return lambda: round(self.earthAge / self.years[planet], 2)
