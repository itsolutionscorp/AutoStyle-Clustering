import functools

class SpaceAge:

    earthYearInSec = 31557600
    planetYears = {'mercury': 0.2408467, 'venus': 0.61519726, 'earth': 1., 'mars': 1.8808158, 
                   'jupiter': 11.862615, 'saturn': 29.447498, 'uranus': 84.016846, 'neptune': 164.79132}

    def __init__(self, seconds):
        self.seconds = float(seconds)

    def __getattr__(self, attr):
        if attr[0:3] == 'on_':
            return functools.partial(self.on_planet, attr[3:])

    def on_planet(self, planet):
        return round(self.seconds/(SpaceAge.earthYearInSec*SpaceAge.planetYears[planet]), 2)
