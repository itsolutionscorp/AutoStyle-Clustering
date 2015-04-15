planet_years = {'Mercury':0.2408467,'Venus': 0.61519726,'Mars': 1.8808158,'Jupiter': 11.862615,'Saturn': 29.447498,'Uranus': 84.016846,'Neptune': 164.79132}
YEAR_ON_EARTH_IN_SECONDS = 60*60*24*365.25

class SpaceAge():

	def __init__(self, seconds):
		self.seconds = seconds
		self.earth_years = self.seconds/YEAR_ON_EARTH_IN_SECONDS

	def on_earth(self):
		return round(self.earth_years, 2)

	def on_mercury(self):
		return round(self.earth_years/planet_years['Mercury'], 2)

	def on_venus(self):
		return round(self.earth_years/planet_years['Venus'], 2)

	def on_mars(self):
		return round(self.earth_years/planet_years['Mars'], 2)

	def on_jupiter(self):
		return round(self.earth_years/planet_years['Jupiter'], 2)

	def on_saturn(self):
		return round(self.earth_years/planet_years['Saturn'], 2)

	def on_uranus(self):
		return round(self.earth_years/planet_years['Uranus'], 2)

	def on_neptune(self):
		return round(self.earth_years/planet_years['Neptune'], 2)

p = SpaceAge(189839836)
print p.on_venus()
