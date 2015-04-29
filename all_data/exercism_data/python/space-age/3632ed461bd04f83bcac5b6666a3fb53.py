EARTH_YEAR = 31557600.
YEAR_LENGTH = {
	"mercury"  : 0.2408467,
	"venus"    : 0.61519726,
	"earth"    : 1,
	"mars"     : 1.8808158,
	"jupiter"  : 11.862615,
	"saturn"   : 29.447498,
	"uranus"   : 84.016846,
	"neptune"  : 164.79132
}

class SpaceAge:
	def age_on(self, planet):
		years = self.seconds / EARTH_YEAR / YEAR_LENGTH[planet]
		return round(years, 2)

	def __init__(self, seconds):
		self.seconds = seconds


def make_func(planet):
	def _function(self):
		return self.age_on(planet)
	return _function

for planet in YEAR_LENGTH.keys():
	setattr(SpaceAge, "on_"+planet, make_func(planet))
