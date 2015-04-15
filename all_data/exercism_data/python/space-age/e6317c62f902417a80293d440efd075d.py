planetkey = {
    'mercury': 0.2408467,
    'venus': 0.61519726,
    'earth': 1.00,
    'mars': 1.8808158,
    'jupiter': 11.862615,
    'saturn': 29.447498,
    'uranus': 84.016846,
    'neptune': 164.79132}

class SpaceAge(object):
    
    
    def __init__(self, age):
        self.seconds = float(age)
        for planet in planetkey:
            setattr(self, 'on_' + planet, self.planet_maker(planet))

    def planet_maker(self, i):
        def make_planet():
            return round((self.seconds / 31557600.0) / planetkey[i], 2)
        return make_planet
