from decimal import Decimal

_ORBITAL_PERIODS = {'earth': 1, 
					'mercury': 0.2408467,
					'venus': 0.61519726,
					'mars': 1.8808158,
					'jupiter': 11.862615,
					'saturn': 29.447498,
					'uranus': 84.016846,
					'neptune': 164.79132}

class SpaceAge(object):
	def __init__(self, age_in_secs):
		self.seconds = age_in_secs

def make_method(cls, planet):
	def calc_age(self):
		age = Decimal(self.seconds)/3600/24/Decimal(365.25)/Decimal(_ORBITAL_PERIODS[planet])
		return round(float(age), 2)
	name = 'on_' + planet
	setattr(cls, name, calc_age)
	
for planet in _ORBITAL_PERIODS:
	  make_method(SpaceAge, planet)
