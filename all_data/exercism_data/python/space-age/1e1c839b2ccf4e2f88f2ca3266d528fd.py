class SpaceAge(object):
    def __init__(self, seconds):
        self.seconds = seconds
        self.SECONDS_IN_EARTH_YEAR = (60 * 60 * 24 * 365.25)
        self.period_planet_map = { 'mercury': 0.2408467,
                                   'venus'  : 0.61519726,
                                   'earth'  : 1,
                                   'mars'   : 1.8808158,
                                   'jupiter': 11.862615,
                                   'saturn' : 29.447498,
                                   'uranus' : 84.016846,
                                   'neptune': 164.79132
                                 }
    
    def __getattribute__(self, name):
        if name[:3]  == 'on_':
            return (lambda: round(self.seconds / self.SECONDS_IN_EARTH_YEAR / self.period_planet_map[name[3:]], 2))
        else:
            return object.__getattribute__(self, name)
