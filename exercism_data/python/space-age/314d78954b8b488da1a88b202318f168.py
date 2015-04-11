class SpaceAge(object):
    EARTH_YEAR_LENGTH_SECONDS = 31557600
    MERCURY_TO_EARTH_RATIO = 0.2408467
    VENUS_TO_EARTH_RATIO = 0.61519726
    MARS_TO_EARTH_RATIO = 1.8808158
    JUPITER_TO_EARTH_RATIO = 11.862615
    SATURN_TO_EARTH_RATIO = 29.447498
    URANUS_TO_EARTH_RATIO = 84.016846
    NEPTUNE_TO_EARTH_RATIO = 164.79132

    def __init__(self, age_seconds):
        self._age_seconds = age_seconds

    @property
    def seconds(self):
        return self._age_seconds

    def on_earth(self):
        return self._seconds_to_planet_years()

    def on_mercury(self):
        return self._seconds_to_planet_years(ratio_to_earth_year=self.MERCURY_TO_EARTH_RATIO)

    def on_venus(self):
        return self._seconds_to_planet_years(ratio_to_earth_year=self.VENUS_TO_EARTH_RATIO)

    def on_mars(self):
        return self._seconds_to_planet_years(ratio_to_earth_year=self.MARS_TO_EARTH_RATIO)

    def on_jupiter(self):
        return self._seconds_to_planet_years(ratio_to_earth_year=self.JUPITER_TO_EARTH_RATIO)

    def on_saturn(self):
        return self._seconds_to_planet_years(ratio_to_earth_year=self.SATURN_TO_EARTH_RATIO)

    def on_uranus(self):
        return self._seconds_to_planet_years(ratio_to_earth_year=self.URANUS_TO_EARTH_RATIO)

    def on_neptune(self):
        return self._seconds_to_planet_years(ratio_to_earth_year=self.NEPTUNE_TO_EARTH_RATIO)

    def _seconds_to_planet_years(self, ratio_to_earth_year=1):
        planet_year_seconds = ratio_to_earth_year * self.EARTH_YEAR_LENGTH_SECONDS
        planet_years = self.seconds / float(planet_year_seconds)
        return round(planet_years, 2)
