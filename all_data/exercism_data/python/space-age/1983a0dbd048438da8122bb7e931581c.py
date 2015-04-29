class SpaceAge:

    def __init__(self, age):
        self.seconds = age
        self.earthyears = self.seconds / 31557600.0

    def on_earth(self):
        return round(self.earthyears, 2)

    def on_mercury(self):
        return round(self.earthyears / 0.2408467, 2)

    def on_venus(self):
        return round(self.earthyears / 0.61519726, 2)

    def on_mars(self):
        return round(self.earthyears / 1.8808158, 2)

    def on_jupiter(self):
        return round(self.earthyears / 11.862615, 2)

    def on_saturn(self):
        return round(self.earthyears / 29.447498, 2)

    def on_uranus(self):
        return round(self.earthyears / 84.016846, 2)

    def on_neptune(self):
        return round(self.earthyears / 164.79132, 2)
