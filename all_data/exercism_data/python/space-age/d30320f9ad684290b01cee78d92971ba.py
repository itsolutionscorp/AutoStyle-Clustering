class SpaceAge():
    earth = 31557600.0

    def __init__(self, seconds):
        self.seconds = seconds

    def on_mercury(self):
        return self.scaled_earth_years(0.2408467)

    def on_venus(self):
        return self.scaled_earth_years(0.61519726)

    def on_earth(self):
        return self.scaled_earth_years(1)

    def on_mars(self):
        return self.scaled_earth_years(1.8808158)

    def on_jupiter(self):
        return self.scaled_earth_years(11.862615)

    def on_saturn(self):
        return self.scaled_earth_years(29.447498)

    def on_neptune(self):
        return self.scaled_earth_years(164.79132)

    def on_uranus(self):
        return self.scaled_earth_years(84.016846)

    def scaled_earth_years(self, scale):
        return round(self.seconds / (self.earth * scale), 2)
