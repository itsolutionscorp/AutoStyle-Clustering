class SpaceAge(object):
    SECONDS_PER_EARTH_YEAR = 31557600.0
    EARTH_YEARS_PER_PLANET_YEAR = {
        "mercury": 0.2408467,
        "venus": 0.61519726,
        "earth": 1,
        "mars": 1.8808158,
        "jupiter": 11.862615,
        "saturn": 29.447498,
        "uranus": 84.016846,
        "neptune": 164.79132
    }

    def __init__(self, seconds):
        self.seconds = seconds

        # i'm feeling lazy.
        # let's generate some closures instead of writing methods by hand.
        for planet, earth_years in self.EARTH_YEARS_PER_PLANET_YEAR.iteritems():
            setattr(self, "on_"+planet, self._make_on_planet(earth_years))

    def _make_on_planet(self, earth_years, precision=2):
        def on_planet():
            return round(self.seconds / self.SECONDS_PER_EARTH_YEAR / earth_years, precision)
        return on_planet
