class SpaceAge:
    def __init__(self, age_seconds):
        self.seconds = age_seconds
        self.earth_year_seconds = 31557600.0

    def _on_xxx(self, orbital_period):
        return round(self.seconds /
                (self.earth_year_seconds * orbital_period), 2)

    def on_earth(self):
        return self._on_xxx(1)

    def on_mercury(self):
        return self._on_xxx(0.2408467)

    def on_venus(self):
        return self._on_xxx(0.61519726)

    def on_mars(self):
        # Can you hear me Major Tom?
        return self._on_xxx(1.8808158)

    def on_jupiter(self):
        return self._on_xxx(11.862615)

    def on_saturn(self):
        return self._on_xxx(29.447498)

    def on_uranus(self):
        return self._on_xxx(84.016846)

    def on_neptune(self):
        return self._on_xxx(164.79132)
