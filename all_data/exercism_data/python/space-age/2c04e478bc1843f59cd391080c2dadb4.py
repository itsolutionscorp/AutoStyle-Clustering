class SpaceAge(object):
    EARTH = 31557600.0
    CONSTANTS = {
        'earth': EARTH * 1.0,
        'mercury': EARTH * 0.2408467,
        'venus': EARTH * 0.61519726,
        'mars': EARTH * 1.8808158,
        'jupiter': EARTH * 11.862615,
        'saturn': EARTH * 29.447498,
        'uranus': EARTH * 84.016846,
        'neptune': EARTH * 164.79132
    }

    def __init__(self, seconds):
        self.seconds = seconds
        for k, v in self.CONSTANTS.iteritems():
            setattr(self, 'on_{0}'.format(k), lambda cf=v: self._convert(cf))

    def _convert(self, cf):
        return round(self.seconds / cf, ndigits=2)
