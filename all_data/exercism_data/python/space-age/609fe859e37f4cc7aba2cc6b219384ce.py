class SpaceAge:
    EARTH_YEAR_IN_SECONDS = 60*60*24*365.25

    def __init__(self, seconds):
        self.seconds = seconds

    def on_mercury(self):
        return self._earth_years_in_orbital(0.2408467)

    def on_venus(self):
        return self._earth_years_in_orbital(0.61519726)

    def on_earth(self):
        return self._earth_years_in_orbital(1)

    def on_mars(self):
        return self._earth_years_in_orbital(1.8808158)

    def on_jupiter(self):
        return self._earth_years_in_orbital(11.862615)

    def on_saturn(self):
        return self._earth_years_in_orbital(29.447498)

    def on_uranus(self):
        return self._earth_years_in_orbital(84.016846)

    def on_neptune(self):
        return self._earth_years_in_orbital(164.79132)

    def _earth_years_in_orbital(self, orbital):
        seconds = self.EARTH_YEAR_IN_SECONDS * orbital
        return round(self.seconds / seconds, 2)
