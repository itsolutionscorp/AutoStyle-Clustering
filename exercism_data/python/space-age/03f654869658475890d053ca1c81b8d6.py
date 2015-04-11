class SpaceAge:
    ORBITALS = {
        "mercury": 0.2408467,
        "venus": 0.61519726,
        "earth": 1,
        "mars": 1.8808158,
        "jupiter": 11.862615,
        "saturn": 29.447498,
        "uranus": 84.016846,
        "neptune": 164.79132
    }

    EARTH_YEAR_IN_SECONDS = 60*60*24*365.25

    def __init__(self, seconds):
        self.seconds = seconds

    def on_mercury(self):
        return self._earth_years_on_planet("mercury")

    def on_venus(self):
        return self._earth_years_on_planet("venus")

    def on_earth(self):
        return self._earth_years_on_planet("earth")

    def on_mars(self):
        return self._earth_years_on_planet("mars")

    def on_jupiter(self):
        return self._earth_years_on_planet("jupiter")

    def on_saturn(self):
        return self._earth_years_on_planet("saturn")

    def on_uranus(self):
        return self._earth_years_on_planet("uranus")

    def on_neptune(self):
        return self._earth_years_on_planet("neptune")

    def _earth_years_on_planet(self, planet):
        seconds = self.EARTH_YEAR_IN_SECONDS * self.ORBITALS[planet]
        return round(self.seconds / seconds, 2)
