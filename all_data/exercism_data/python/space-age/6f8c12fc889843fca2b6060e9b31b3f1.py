class SpaceAge():

    EARTH_ORBITAL_PERIOD = 31557600

    def __init__(self, age_in_seconds):
        self.seconds = age_in_seconds

    def _on_earth_no_rounding(self):
        return self.seconds / SpaceAge.EARTH_ORBITAL_PERIOD

    def on_earth(self):
        return round(self.seconds / SpaceAge.EARTH_ORBITAL_PERIOD, 2)

    def on_mercury(self):
        return round(self._on_earth_no_rounding() / 0.2408467, 2)

    def on_venus(self):
        return round(self._on_earth_no_rounding() / 0.61519726, 2)

    def on_mars(self):
        return round(self._on_earth_no_rounding() / 1.8808158, 2)

    def on_jupiter(self):
        return round(self._on_earth_no_rounding() / 11.862615, 2)

    def on_saturn(self):
        return round(self._on_earth_no_rounding() / 29.447498, 2)

    def on_uranus(self):
        return round(self._on_earth_no_rounding() / 84.016846, 2)

    def on_neptune(self):
        return round(self._on_earth_no_rounding() / 164.79132, 2)
