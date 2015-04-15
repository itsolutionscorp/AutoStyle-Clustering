#!/usr/bin/env python3
class SpaceAge:
    def __init__(self, seconds):
        self.seconds = seconds
        self.planets = {'Earth': 31557600,
                        'Mercury': 31557600 * 0.2408467,
                        'Venus': 31557600 * 0.61519726,
                        'Mars': 31557600 * 1.8808158,
                        'Jupiter': 31557600 * 11.862615,
                        'Saturn': 31557600 * 29.447498,
                        'Uranus': 31557600 * 84.016846,
                        'Neptune': 31557600 * 164.79132}

    def age(self, planet):
        return round(self.seconds / self.planets[planet], 2)

    def on_earth(self):
        return self.age('Earth')

    def on_mercury(self):
        return self.age('Mercury')

    def on_venus(self):
        return self.age('Venus')

    def on_mars(self):
        return self.age('Mars')

    def on_jupiter(self):
        return self.age('Jupiter')

    def on_saturn(self):
        return self.age('Saturn')

    def on_uranus(self):
        return self.age('Uranus')

    def on_neptune(self):
        return self.age('Neptune')
