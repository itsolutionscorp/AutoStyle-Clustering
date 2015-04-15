YEAR = 31557600.0

class SpaceAge:
    def __init__(self, seconds):
        self.seconds = seconds

    def on_earth(self):
        return self.format(1.0)

    def on_uranus(self):
        return self.format(84.016846)

    def on_saturn(self):
        return self.format(29.447498)

    def on_neptune(self):
        return self.format(164.79132)

    def on_mars(self):
        return self.format(1.8808158)

    def on_jupiter(self):
        return self.format(11.862615)

    def on_venus(self):
        return self.format(0.61519726)

    def on_mercury(self):
        return self.format(0.2408467)

    def format(self, planet):
        return float(int(self.seconds / YEAR / planet * 100 + 0.5) / 100.0)
