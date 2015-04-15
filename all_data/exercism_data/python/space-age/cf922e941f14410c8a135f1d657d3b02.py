class SpaceAge:
	'''
	Given an age in seconds, calculates how old someone is in terms of a given planet's solar years
	'''
	
	def __init__(self,seconds):
		secs_per_year = 31557600 # seconds in an earth year	
		periods = {
			'mercury': 0.2408467,
			'venus': 0.61519726,
			'earth': 1.0,
			'mars': 1.8808158,
			'jupiter': 11.862615,
			'saturn': 29.447498,
			'uranus': 84.016846,
			'neptune': 164.79132
		}
		self.seconds = seconds
		
		# for each planet create a method that returns the age in that planet's years
		for planet in periods:
			# setting a default value for planet (planet=planet) is needed to put 
			#   planet in the scope of the lambda expression, rather than the global scope
			setattr(self, ''.join(('on_',planet)), 
				lambda planet=planet: round(self.seconds / secs_per_year / periods[planet] , 2))
		
if __name__ == '__main__':
	age = SpaceAge(1e6)
	dir(age)
	print(age.on_earth())
	print(age.on_venus())
