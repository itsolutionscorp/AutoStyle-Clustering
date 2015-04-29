import sys

class SpaceAge:
    _planets = {
        'earth': 31557600.0 * 1.0,
        'mercury': 31557600.0 * 0.2408467,
        'venus': 31557600.0 * 0.61519726,
        'mars': 31557600.0 * 1.8808158,
        'jupiter': 31557600.0 * 11.862615,
        'saturn': 31557600.0 * 29.447498,
        'uranus': 31557600.0 * 84.016846,
        'neptune': 31557600.0 * 164.79132,
    }

    def __init__(self, age):
        self.seconds = age
        for planet in self._planets:
            setattr(self.__class__, 'on_'+planet, lambda s,p=planet: s.on(p))

    def on(self, planet):
        if not planet in self._planets:
            return None
        return round(self.seconds / self._planets[planet], 2)
