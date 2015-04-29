import functools


class SpaceAge(object):
    # One Earth year, in seconds
    EARTH_YEAR = 31557600

    # Orbital periods of planets, in Earth years
    ORBITS = {
        'mercury': 0.2408467,
        'venus': 0.61519726,
        'earth': 1.0,
        'mars': 1.8808158,
        'jupiter': 11.862615,
        'saturn': 29.447498,
        'uranus': 84.016846,
        'neptune': 164.79132,
    }

    def __init__(self, seconds):
        self.seconds = seconds

    def _get_years(self, orbital_period):
        """
        Get the number of years in the given orbital period
        """
        return round(self.seconds / (self.EARTH_YEAR * orbital_period), 2)

    def __getattr__(self, name):
        # Supply an implementation for on_<planet> based on _get_years
        # for each planet in ORBITS
        if name.startswith('on_'):
            planet = name[3:]
            if planet in self.ORBITS:
                return functools.partial(self._get_years, self.ORBITS[planet])

        raise AttributeError
