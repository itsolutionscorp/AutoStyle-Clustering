SECONDS_PER_YEAR = 31557600

CONVERSION_FACTORS = {
    'earth': 1,
    'mercury': 0.2408467,
    'venus': 0.61519726,
    'mars': 1.8808158,
    'jupiter': 11.862615,
    'saturn': 29.447498,
    'uranus': 84.016846,
    'neptune': 164.79132
}


class SpaceAge(object):
    def __init__(self, seconds):
        self.seconds = seconds
        self._years = 1.0 * self.seconds / SECONDS_PER_YEAR

    def __getattr__(self, attr):
        if not attr.startswith('on_') or attr[3:] not in CONVERSION_FACTORS:
            raise AttributeError(attr)
        return lambda: round(self._years / CONVERSION_FACTORS[attr[3:]], 2)
