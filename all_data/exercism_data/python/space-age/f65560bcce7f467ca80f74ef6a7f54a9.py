class SpaceAge():

    _planet_year_multipliers = {
        'mercury': 0.2408467,
        'venus': 0.61519726,
        'earth': 1,
        'mars': 1.8808158,
        'jupiter': 11.862615,
        'saturn': 29.447498,
        'uranus': 84.016846,
        'neptune': 164.79132,
    }

    def __init__(self, seconds):
        self.seconds = seconds
        self._earth_years = seconds / 31557600

for planet in SpaceAge._planet_year_multipliers.keys():
    # set _planet here so it is set at function definition time,
    # not as a closure
    def method(self, _planet=planet):
        m = self._planet_year_multipliers[_planet]
        return round(self._earth_years / m, 2)
    method.__name__ = 'on_{}'.format(planet)
    setattr(SpaceAge, method.__name__, method)
