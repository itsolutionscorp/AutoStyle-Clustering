
class SpaceAge:
	def __init__(self, seconds):
		self.seconds = seconds
		self.earthSeconds = 31557600
		self.earthAge = float(seconds / float(self.earthSeconds))
		self.years = {'Mercury':0.2408467, 'Venus':0.61519726, 'Earth': 1, 'Mars': 1.8808158,
				'Jupiter': 11.862615, 'Saturn': 29.447498, 'Uranus':84.016846, 'Neptune':164.79132}

	def on_earth(self):
		return round(self.earthAge,2)
	
	def on_mercury(self):
		return round(self.earthAge / self.years['Mercury'], 2)
		
	def on_venus(self):
		return round(self.earthAge / self.years['Venus'], 2)
	
	def on_mars(self):
		return round(self.earthAge / self.years['Mars'], 2)
	
	def on_jupiter(self):
		return round(self.earthAge / self.years['Jupiter'], 2)
	
	def on_saturn(self):
		return round(self.earthAge / self.years['Saturn'], 2)
	
	def on_uranus(self):
		return round(self.earthAge / self.years['Uranus'], 2)
	
	def on_neptune(self):
		return round(self.earthAge / self.years['Neptune'], 2)
