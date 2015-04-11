class SpaceAge:
    def __init__(self, seconds):
        self.seconds = seconds
        self._on_earth = seconds / 31557600

    def on_earth(self):
        return round(self._on_earth, 2)

    def on_mercury(self):
        return round(self._on_earth / 0.2408467, 2)
