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

    def __init__(self, seconds, precision=2):
        self.seconds = seconds
        self.precision = precision

    def __getattr__(self, key):
        key = key.split("_")
        if (len(key) == 2 and key[0] == "on"
                and key[1] in self.EARTH_YEARS_PER_PLANET_YEAR):
            return lambda: round(
                self.seconds /
                    self.SECONDS_PER_EARTH_YEAR /
                    self.EARTH_YEARS_PER_PLANET_YEAR[key[1]],
                self.precision
            )
        raise AttributeError("No attribute '{}'".format('_'.join(key)))
