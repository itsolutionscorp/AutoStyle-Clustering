from __future__ import division

class SpaceAge(object):
    table = {
        'earth': 1,
        'mercury': 0.2408467,
        'venus': 0.61519726,
        'mars': 1.8808158,
        'jupiter': 11.862615,
        'saturn': 29.447498,
        'uranus': 84.016846,
        'neptune': 164.79132,
    }
        
    def __init__(self, number):
        self.seconds = number
    def __getattr__(self, attr):
        if attr.startswith('on_'):
            planet = attr[3:]
            factor = self.table.get(planet)
            if factor:
                return lambda: round(self.seconds / (31557600 * factor), 2)
        return super(SpaceAge, self).__getattr__(attr)
