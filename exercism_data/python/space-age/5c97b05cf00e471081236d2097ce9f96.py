"Given an age in seconds, calculate how old someone would be on given planet."


class SpaceAge(object):
    _EARTH_YEARS = {
        'mercury': 0.2408467,
        'venus':   0.61519726,
        'earth':   1.0,
        'mars':    1.8808158,
        'jupiter': 11.862615,
        'saturn':  29.447498,
        'uranus':  84.016846,
        'neptune': 164.79132
    }

    def __init__(self, seconds):
        self.seconds = seconds

    def __getattr__(self, attr):
        """Return year calculations rounded to 2 decimal precision
           for any on_planet method with valid planet."""
        prefix, planet = attr.partition("_")[::2]
        if prefix == 'on' and planet in self._EARTH_YEARS:
            def onMethod():
                return round(self._on_earth() / self._EARTH_YEARS[planet], 2)
            return onMethod
        return self.__getattribute__(attr)

    def _on_earth(self):
        """Earth: orbital period 365.25 Earth days, or 31557600 seconds
           All year calculations based on the Earth case."""
        return self.seconds / 31557600.0
