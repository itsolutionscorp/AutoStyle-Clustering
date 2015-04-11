class SpaceAge:
    def __init__(self, seconds):
        self.seconds = seconds
        self._on_earth = seconds / 31557600

    def on_earth(self):
        return round(self._on_earth, 2)

    def on_mercury(self):
        return round(self._on_earth / 0.2408467, 2)

    def on_venus(self):
        return round(self._on_earth / 0.61519726, 2)

    def on_mars(self):
        return round(self._on_earth / 1.8808158, 2)

    def on_jupiter(self):
        return round(self._on_earth / 11.862615, 2)

    def on_saturn(self):
        return round(self._on_earth / 29.447498, 2)

    def on_uranus(self):
        return round(self._on_earth / 84.016846, 2)

    def on_neptune(self):
        return round(self._on_earth / 164.79132, 2)
