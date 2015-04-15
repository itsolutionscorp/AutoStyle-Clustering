class SpaceAge:

	def __init__(self,seconds):
		self.seconds=float(seconds)

	def on_earth(self):
		return round(self.seconds / float(31557600), 2)

	
	def on_mercury(self):
		return round((self.seconds /float(31557600))/float(0.2408467),2)
	
	def on_venus(self):
		return round((self.seconds /float(0.61519726))/float(31557600),2)
		
	def on_mars(self):
		return round((self.seconds /float(1.8808158))/float(31557600),2)
		
	def on_jupiter(self):
		return round((self.seconds/float(11.862615))/float(31557600),2)

	def on_saturn(self):
		return round((self.seconds/float(29.447498))/float(31557600),2)

	def on_uranus(self):
		return round((self.seconds/float(84.016846))/float(31557600),2)
		
	def on_neptune(self):
		return round((self.seconds/float(164.79132))/float(31557600),2)
