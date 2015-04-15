class SpaceAge(object):
    PLANETS = {'earth': 1.0,  
               'mercury': 0.2408467,
               'venus': 0.61519726,
               'mars': 1.8808158,
               'jupiter': 11.862615,
               'saturn': 29.447498,
               'uranus': 84.016846,
               'neptune': 164.79132}

    EARTH_YEAR_SEC = 31557600.0

    def __init__(self, seconds):
        self.seconds = seconds

    def __getattr__(self, item):
        if item[3:] in self.PLANETS:
            return lambda: round(self.seconds / (self.EARTH_YEAR_SEC * self.PLANETS[item[3:]]), 2)
