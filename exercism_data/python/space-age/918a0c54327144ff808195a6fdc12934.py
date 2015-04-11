class SpaceAge(object):

    orbit_periods = {
        'earth': 31557600,
        'mercury': 31557600*0.2408467,
        'venus': 31557600*0.61519726,
        'mars': 31557600*1.8808158,
        'jupiter': 31557600*11.862615,
        'saturn': 31557600*29.447498,
        'uranus': 31557600*84.016846,
        'neptune': 31557600*164.79132,
    }

    def __init__(self, seconds):
        self.seconds = seconds

    def __getattr__(self, attr):
        if not attr[:3] == 'on_':
            raise AttributeError('Invalid attribute.')
        elif attr[3:] not in self.orbit_periods:
            raise KeyError('No such planet!')
        else:
            return lambda: round(self.seconds / float(self.orbit_periods[attr[3:]]), 2)

    
