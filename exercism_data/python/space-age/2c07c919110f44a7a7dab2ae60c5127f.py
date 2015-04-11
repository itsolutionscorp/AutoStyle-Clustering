#space_age.py
#SPAAAAAAAAAAAAAAAAAAAAAAAAACE


class SpaceAge:

    earth_seconds = 31557600

    def __init__(self, seconds):
        self.seconds = seconds

    def on_mercury(self):
        return round(self.seconds/(0.2408467*self.earth_seconds), ndigits=2)

    def on_venus(self):
        return round(self.seconds/(0.61519726*self.earth_seconds), ndigits=2)

    def on_earth(self):
        return round(self.seconds/float(self.earth_seconds), ndigits=2)

    def on_mars(self):
        return round(self.seconds/(1.8808158*self.earth_seconds), ndigits=2)

    def on_jupiter(self):
        return round(self.seconds/(11.862615*self.earth_seconds), ndigits=2)

    def on_saturn(self):
        return round(self.seconds/(29.447498*self.earth_seconds), ndigits=2)

    def on_uranus(self):
        return round(self.seconds/float(84.016846*self.earth_seconds), ndigits=2)

    def on_neptune(self):
        return round(self.seconds/(164.79132*self.earth_seconds), ndigits=2)
