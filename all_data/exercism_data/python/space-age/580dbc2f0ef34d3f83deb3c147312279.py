__author__ = 'tracyrohlin'

class SpaceAge:
    def __init__(self, age):
        self.age = float(age)
        self.earth_sec = 31557600 * 1.0000000
        self.mercury_percent = 31557600 * 0.2408467
        self.venus_percent = 31557600 * 0.61519726
        self.mars_percent = 31557600 * 1.8808158
        self.jupiter_percent = 31557600 * 11.862615
        self.saturn_percent = 31557600 * 29.447498
        self.uranus_percent = 31557600 * 84.016846
        self.neptune_percent = 31557600 * 164.79132

    def seconds(self):
        return self.age

    def on_earth(self):
        earth_years = (self.age) / self.earth_sec
        result = ("%.2f" % earth_years)
        return float(result)

    def on_mercury(self):
        mercury_years = (self.age) / self.mercury_percent
        result = ("%.2f" % mercury_years)
        return float(result)

    def on_venus(self):
        venus_years = self.age / self.venus_percent
        result = ("%.2f" % venus_years)
        return float(result)

    def on_mars(self):
        mars_years = self.age / self.mars_percent
        result = ("%.2f") % mars_years
        return float(result)

    def on_jupiter(self):
        jupiter_years = self.age / self.jupiter_percent
        result = ("%.2f") % jupiter_years
        return float(result)

    def on_saturn(self):
        saturn_years = self.age / self.saturn_percent
        result = ("%.2f") % saturn_years
        return float(result)

    def on_uranus(self):
        uranus_years = self.age / self.uranus_percent
        result = ("%.2f") % uranus_years
        return float(result)

    def on_neptune(self):
        neptune_years = self.age / self.neptune_percent
        result = ("%.2f") % neptune_years
        return float(result)
