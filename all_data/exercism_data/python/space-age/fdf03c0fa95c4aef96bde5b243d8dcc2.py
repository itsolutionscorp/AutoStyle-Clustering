from __future__ import division

'''
	This class represents the implementation of the solution to
	the SpaceAge exercise
'''
class SpaceAge(object):
	mercury_orbital = 0.2408467
	venus_orbital = 0.61519726
	mars_orbital = 1.8808158
	jupiter_orbital = 11.862615
	saturn_orbital = 29.447498
	uranus_orbital = 84.016846
	neptune_orbital = 164.79132

	'''
		Constructor to initialize the program
	'''
	def __init__(self, seconds):
		self.seconds = seconds
		self.base = 31557600

	'''
		Get the age in Mars years
	'''
	def on_mercury(self):
		return round((self.seconds/(self.base * self.mercury_orbital)), 2)

	'''
		Get the age in Venus years
	'''
	def on_venus(self):
		return round((self.seconds/(self.base * self.venus_orbital)), 2)

	'''
		Get the age in Earth years
	'''
	def on_earth(self):
		return round(self.seconds/self.base, 2)

	'''
		Get the age in Mars years
	'''
	def on_mars(self):
		return round((self.seconds/(self.base * self.mars_orbital)), 2)

	'''
		Get the age in Jupiter years
	'''
	def on_jupiter(self):
		return round((self.seconds/(self.base * self.jupiter_orbital)), 2)

	'''
		Get the age in Saturn years
	'''
	def on_saturn(self):
		return round((self.seconds/(self.base * self.saturn_orbital)), 2)

	'''
		Get the age in Neptune years
	'''
	def on_neptune(self):
		return round((self.seconds/(self.base * self.neptune_orbital)), 2)

	'''
		Get the age in Uranus years
	'''
	def on_uranus(self):
		return round((self.seconds/(self.base * self.uranus_orbital)), 2)
