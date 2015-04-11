class SpaceAge:

	def __init__(self, number):
		self.number = number
		self.earthage = self.number
		self.earthage /= 60.0 # to mins
		self.earthage /= 60.0 # to hrs
		self.earthage /= 24.0 # to days
		self.earthage /= 365.25 # to years
	
	# properties don't require () 
	@property
	def seconds(self):
		return self.number

	def on_earth(self):
		return round(self.earthage, 2)

	def on_venus(self):
		return round(self.earthage / 0.61519726, 2)

	def on_mercury(self):
		return round(self.earthage / 0.2408467, 2)

	def on_neptune(self):
		return round(self.earthage / 164.79132, 2)

	def on_uranus(self):
		return round(self.earthage / 84.016846, 2)

	def on_saturn(self):
		return round(self.earthage / 29.447498, 2)

	def on_jupiter(self):
		return round(self.earthage / 11.862615, 2)

	def on_mars(self):
		return round(self.earthage / 1.8808158, 2)
