import types

PERIODS = dict(zip(
    'earth,mercury,venus,mars,jupiter,saturn,uranus,neptune'.split(','),
    (1.0, 0.2408467, 0.61519726, 1.8808158, 11.862615, 29.447498, 84.016846,
     164.79132)
))
SECONDS_IN_EARTH_YEAR = 365.25 * 24 * 60 * 60

def make_on_planet(seconds, period):
    return lambda: round(seconds / period / SECONDS_IN_EARTH_YEAR, 2)

class SpaceAge(object):
    def __init__(self, seconds):
        self._seconds = None
        self.seconds = seconds

    @property
    def seconds(self):
        return self._seconds

    @seconds.setter
    def seconds(self, val):
        self._seconds = val
        for planet, period in PERIODS.items():
            setattr(self, 'on_{}'.format(planet), make_on_planet(val, period))
