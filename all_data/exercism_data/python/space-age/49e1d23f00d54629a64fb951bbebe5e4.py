# Given an age in seconds, calculate how old someone is in terms of 
# a given planet's solar years. 
import math

def space_age(planet):
	def divisor(self):
		return round(self.seconds / planet, 2)
	return divisor 

class SpaceAge(object):

		on_earth = space_age(31557600.0)
		on_venus = space_age(19413907.2)
		on_neptune = space_age(5200418592.0)
		on_uranus = space_age(2661041808.0)
		on_mars = space_age(59354294.4)
		on_mercury = space_age(7600530.24)
		on_saturn = space_age(929596608.0)
		on_jupiter = space_age(374335776.0)

		def __init__(self, seconds):
			self.seconds = seconds
