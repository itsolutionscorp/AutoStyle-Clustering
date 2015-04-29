EARTH_ORBIT = 31557600

ORBITS = {
	"Earth": 1,
	"Mercury": 0.2408467,
	"Venus": 0.61519726,
	"Mars": 1.8808158,
	"Jupiter": 11.862615,
	"Saturn": 29.447498,
	"Uranus": 84.016846,
	"Neptune": 164.79132
	}


class SpaceAge:
	

	def __init__(self, age):
		self.seconds = age

	def on_earth(self):
		return(round(float(self.seconds) / EARTH_ORBIT, 2))

	def on_mercury(self):
		return(round(self.seconds / EARTH_ORBIT / ORBITS["Mercury"], 2))

	def on_venus(self):
		return(round(self.seconds / EARTH_ORBIT / ORBITS["Venus"], 2))

	def on_mars(self):
		return(round(self.seconds / EARTH_ORBIT / ORBITS["Mars"], 2))

	def on_jupiter(self):
		return(round(self.seconds / EARTH_ORBIT / ORBITS["Jupiter"], 2))

	def on_saturn(self):
		return(round(self.seconds / EARTH_ORBIT / ORBITS["Saturn"], 2))

	def on_uranus(self):
		return(round(self.seconds / EARTH_ORBIT / ORBITS["Uranus"], 2))

	def on_neptune(self):
		return(round(self.seconds / EARTH_ORBIT / ORBITS["Neptune"], 2))
