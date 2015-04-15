__author__ = 'Hinek'

YEARS = {'Mercury': 0.2408467,
         'Venus': 0.61519726,
         'Earth': 1,
         'Mars': 1.8808158,
         'Jupiter': 11.862615,
         'Saturn': 29.447498,
         'Uranus': 84.016846,
         'Neptune': 164.79132}

EARTH_YEAR_SECONDS = 31557600.0


class SpaceAge(object):

    def __init__(self, seconds):
        self.seconds = seconds

    def on_earth(self):
        return self.calculate(YEARS['Earth'])

    def on_mercury(self):
        return self.calculate(YEARS['Mercury'])

    def on_venus(self):
        return self.calculate(YEARS['Venus'])

    def on_mars(self):
        return self.calculate(YEARS['Mars'])

    def on_jupiter(self):
        return self.calculate(YEARS['Jupiter'])

    def on_saturn(self):
        return self.calculate(YEARS['Saturn'])

    def on_uranus(self):
        return self.calculate(YEARS['Uranus'])

    def on_neptune(self):
        return self.calculate(YEARS['Neptune'])

    def calculate(self, year):
        return round(1 / EARTH_YEAR_SECONDS / year * self.seconds, 2)
