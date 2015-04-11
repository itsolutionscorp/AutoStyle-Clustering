# orbital periods expressed in Earth years
PLANETS = {'earth':     1.0,
           'mercury':   0.2408467,
           'venus':     0.61519726,
           'mars':      1.8808158,
           'jupiter':   11.862615,
           'saturn':    29.447498,
           'uranus':    84.016846,
           'neptune':   164.79132}

ONE_YEAR = 365.25 * 24 * 60 * 60  # in seconds


class SpaceAge(object):
    def __init__(self, seconds): 
        self.seconds = seconds

    def __getattr__(self, item):
        if item[3:] in PLANETS:  # item[3:] extracts the name of the desired planet
            return lambda: round(self.seconds / (ONE_YEAR * PLANETS[item[3:]]), 2)
