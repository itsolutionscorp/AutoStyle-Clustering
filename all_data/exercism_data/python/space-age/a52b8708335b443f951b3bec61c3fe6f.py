EARTH_SECONDS = 31557600.0
PLANETS = {"mercury":0.2408467, "venus":0.61519726, "mars":1.8808158,
           "jupiter":11.862615, "saturn":29.447498, "uranus":84.016846,
           "neptune":164.79132,}


class SpaceAge():
    def __init__(self, seconds, precision=2):
        self.seconds = seconds
        self.pre = precision

    def on_mercury(self):
        return round(self.seconds / EARTH_SECONDS / PLANETS["mercury"], self.pre)

    def on_venus(self):
        return round(self.seconds / EARTH_SECONDS / PLANETS["venus"], self.pre)

    def on_earth(self):
        return round(self.seconds / EARTH_SECONDS, self.pre)

    def on_mars(self):
        return round(self.seconds / EARTH_SECONDS / PLANETS["mars"], self.pre)

    def on_jupiter(self):
        return round(self.seconds / EARTH_SECONDS / PLANETS["jupiter"], self.pre)

    def on_saturn(self):
        return round(self.seconds / EARTH_SECONDS / PLANETS["saturn"], self.pre)

    def on_uranus(self):
        return round(self.seconds / EARTH_SECONDS / PLANETS["uranus"], self.pre)

    def on_neptune(self):
        return round(self.seconds / EARTH_SECONDS / PLANETS["neptune"], self.pre)
