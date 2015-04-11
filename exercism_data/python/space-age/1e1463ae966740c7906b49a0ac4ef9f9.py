"""Years in terms of a given planet's solar years."""


# Earth's periods of revolution in seconds
_EARTH_PERIOD_OF_REV_IN_SECS = 31557600.0

#: Planets' periods of revolution in seconds
PERIOD_OF_REV_IN_SECS = {
    "earth": _EARTH_PERIOD_OF_REV_IN_SECS,
    "mercury": 0.2408467 * _EARTH_PERIOD_OF_REV_IN_SECS,
    "venus": 0.61519726 * _EARTH_PERIOD_OF_REV_IN_SECS,
    "mars": 1.8808158 * _EARTH_PERIOD_OF_REV_IN_SECS,
    "jupiter": 11.862615 * _EARTH_PERIOD_OF_REV_IN_SECS,
    "saturn": 29.447498 * _EARTH_PERIOD_OF_REV_IN_SECS,
    "uranus": 84.016846 * _EARTH_PERIOD_OF_REV_IN_SECS,
    "neptune": 164.79132 * _EARTH_PERIOD_OF_REV_IN_SECS
}


class SpaceYear(object):
    """Years in terms of a given planet's solar years.

    SpaceYear.on_*planet*() returns years in terms of *planet*'s solar
    years when *planet* is among PERIOD_OF_REV_IN_SECS's keys.
    """

    def __init__(self, seconds):
        """Create a space year that corresponds to a period in seconds."""
        self.seconds = seconds

    def __getattr__(self, name):
        if name.startswith("on_"):
            planet = name[3:]
            if planet in PERIOD_OF_REV_IN_SECS:
                return lambda prec=2: self.on_planet(planet, prec)
        raise AttributeError(
            "'{!s}' object has no attribute '{!s}'".format(
                self.__class__.__name__, name))

    def __repr__(self):
        return "{!s}({!r})".format(self.__class__.__name__, self.seconds)

    def __str__(self):
        return "{}".format(self.seconds)

    def on_planet(self, planet, prec=2):
        """Convert to a planet's solar years.

        :type planet: str.
        :param prec: a precision in decimal digits (default 2 digits).
        """
        years = self.seconds / PERIOD_OF_REV_IN_SECS[planet]
        return round(years, prec)


# ``class SpaceAge`` is an alias of ``class SpaceYear``.
SpaceAge = SpaceYear
