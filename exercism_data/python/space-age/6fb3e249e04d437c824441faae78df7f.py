class SpaceAge(object):
	def __init__(self, seconds):
		self.seconds = seconds
		self.earth_year = 31557600.00
		self.earth_age = self.seconds/self.earth_year
		
	def on_mercury(self):
		x = "%.2f" % (self.earth_age/0.2408467)
		return float(x)
	
	def on_venus(self):
		x = "%.2f" % (self.earth_age/0.61519726)
		return float(x)
	
	def on_earth(self):
		x = "%.2f" % self.earth_age
		return float(x)
	
	def on_mars(self):
		x = "%.2f" % (self.earth_age/1.8808158)
		return float(x)
	
	def on_jupiter(self):
		x = "%.2f" % (self.earth_age/11.862615)
		return float(x)
	
	def on_saturn(self):
		x = "%.2f" % (self.earth_age/29.447498)
		return float(x)
	
	def on_uranus(self):
		x = "%.2f" % (self.earth_age/84.016846)
		return float(x)
	
	def on_neptune(self):
		x = "%.2f" % (self.earth_age/164.79132)
		return float(x)
	
	
