EARTH_YEAR_IN_SECONDS = 31557600.0

PLANET_YEARS = {'earth': 1.0, 'mercury': 0.2408467,
                'venus': 0.61519726, 'mars': 1.8808158,
                'jupiter': 11.862615, 'saturn': 29.447498,
                'uranus': 84.016846, 'neptune': 164.79132}


class SpaceAge(object):

    """Age calculator for different planets."""

    def __init__(self, seconds):
        """Create new age calculator with given age in earth seconds."""
        self.seconds = seconds
        for planet in PLANET_YEARS:
            self._add_method(planet)

    def _on_planet(self, planet):
        "Return age in planet years."
        return round(self.seconds / EARTH_YEAR_IN_SECONDS /
                     PLANET_YEARS[planet], 2)

    def _add_method(self, planet):
        """Add method to return age in planet years."""
        func = lambda: self._on_planet(planet)
        func.__name__ = "on_" + planet
        func.__doc__ = "Return age in " + planet + " years."
        self.__setattr__(func.__name__, func)
