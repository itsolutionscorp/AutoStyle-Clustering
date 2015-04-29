__author__ = 'angelo'

PLANET_FACTORS = dict(Earth=1.0, Mercury=0.2408467, Venus=0.61519726, Mars=1.8808158, Jupiter=11.862615, Saturn=29.447498,
                      Uranus=84.016846, Neptune=164.79132)
EARTH_YEAR_IN_SECONDS = 31557600.0


class SpaceAge:
    def __init__(self, age_in_seconds):
        self.seconds = age_in_seconds

    def _planet_age(self, planet):
        return round((self.seconds / EARTH_YEAR_IN_SECONDS) / PLANET_FACTORS[planet], 2)

    def on_earth(self):
        return self._planet_age("Earth")

    def on_mercury(self):
        return self._planet_age("Mercury")

    def on_venus(self):
        return self._planet_age("Venus")

    def on_mars(self):
        return self._planet_age("Mars")

    def on_jupiter(self):
        return self._planet_age("Jupiter")

    def on_saturn(self):
        return self._planet_age("Saturn")

    def on_uranus(self):
        return self._planet_age("Uranus")

    def on_neptune(self):
        return self._planet_age("Neptune")
