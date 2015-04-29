class SpaceAge:
	def __init__(self,n):
		self.seconds = n
	def on_earth(self):
		return round(self.seconds/float(31557600),2)
	def on_mercury(self):
		return round(.2408467**(-1)*self.seconds/float(31557600),2)
	def on_venus(self):
		return round(.61519726**(-1)* self.seconds/float(31557600),2)
	def on_mars(self):
		return round(1.8808158**(-1)* self.seconds/float(31557600),2)
	def on_jupiter(self):
		return round(11.862615**(-1)* self.seconds/float(31557600),2)
	def on_saturn(self):
		return round(29.447498**(-1)* self.seconds/float(31557600),2)
	def on_uranus(self):
		return round(84.016846**(-1)* self.seconds/float(31557600),2)
	def on_neptune(self):
		return round(164.79132**(-1)* self.seconds/float(31557600),2)
