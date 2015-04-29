class SpaceAge:
	
	def __init__(self, age):
		self.seconds = age
		self.earthAge = age/31557600
		
	def on_earth(self):
		return round(self.earthAge,2)
	
	def on_mercury(self):
		return round(self.earthAge*0.2408467,2)
	
	def on_venus(self):
		return round(self.earthAge*0.61519726,2)
	
	def on_mars(self):
		return round(self.earthAge*1.8808158,2)
	
	def on_jupiter(self):
		return round(self.earthAge*11.862615,2)
	
	def on_saturn(self):
		return round(self.earthAge*29.447498,2)
	
	def on_uranus(self):
		return round(self.earthAge*84.016846,2)
	
	def on_neptune(self):
		return round(self.earthAge*164.79132,2)
	
if __name__ == '__main__':
	space = SpaceAge(2134835688)
	print space.seconds
	print space.on_earth()
	print space.on_venus()
