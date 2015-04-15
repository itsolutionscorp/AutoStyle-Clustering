__author__ = 'agupt15'

EARTH_AGE = float(31557600)
AGE_MAP = {'earth': EARTH_AGE, 'mercury': EARTH_AGE * 0.2408467, 'venus': EARTH_AGE * 0.61519726,
           'mars': EARTH_AGE * 1.8808158, 'jupiter': EARTH_AGE * 11.862615, 'saturn': EARTH_AGE * 29.447498,
           'uranus': EARTH_AGE * 84.016846, 'neptune': EARTH_AGE * 164.79132}


class SpaceAge:
    def __init__(self, age):
        self.seconds = age

    def on_earth(self):
        return self.calculate('earth')

    def on_mars(self):
        return self.calculate('mars')

    def on_mercury(self):
        return self.calculate('mercury')

    def on_venus(self):
        return self.calculate('venus')

    def on_jupiter(self):
        return self.calculate('jupiter')

    def on_saturn(self):
        return self.calculate('saturn')

    def on_uranus(self):
        return self.calculate('uranus')

    def on_neptune(self):
        return self.calculate('neptune')

    def calculate(self, planet):
        return round(self.seconds / AGE_MAP[planet], 2)

spaceAge = SpaceAge(1e10)
print(spaceAge.on_earth())
