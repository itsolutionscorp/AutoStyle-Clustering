import decimal

class SpaceAge(object):

    EARTH_YEAR_IN_SEC = 31557600

    def __init__(self, seconds):
        self.seconds = seconds
        self.age = float(seconds)/31557600

    def on_earth(self):
        return round(float(self.seconds)/SpaceAge.EARTH_YEAR_IN_SEC, 2)

    def on_mercury(self):
        return round(self.age/0.2408467, 2)

    def on_venus(self):
        return round(self.age/0.61519726, 2)

    def on_mars(self):
        return round(self.age/1.8808158, 2)

    def on_jupiter(self):
        return round(self.age/11.862615, 2)

    def on_saturn(self):
        return round(self.age/29.447498, 2)

    def on_uranus(self):
        return round(self.age/84.016846, 2)

    def on_neptune(self):
        return round(self.age/164.79132, 2)
