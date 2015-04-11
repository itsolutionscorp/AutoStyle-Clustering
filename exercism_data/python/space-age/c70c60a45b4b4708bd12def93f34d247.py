from decimal import *
class SpaceAge:
    """ SpaceAge calculates someones age on different planets """

    EARTH = 31557600 
    MERCURY = 0.2408467 
    VENUS = 0.61519726
    MARS = 1.8808158 
    JUPITER = 11.862615 
    SATURN = 29.447498 
    URANUS = 84.016846 
    NEPTUNE = 164.79132

    def __init__(self, age):
        self.seconds = Decimal(age)

    def on_earth(self):
        return round(self.seconds / self.EARTH, 2)

    def on_mercury(self):
        return round(self.on_earth() / self.MERCURY, 2)

    def on_venus(self):
        # Rounding error, needed to use more precise value
        earth = self.seconds / self.EARTH
        return round(earth / Decimal(self.VENUS), 2)

    def on_mars(self):
        return round(self.on_earth() / self.MARS, 2)

    def on_jupiter(self):
        return round(self.on_earth() / self.JUPITER, 2)

    def on_saturn(self):
        return round(self.on_earth() / self.SATURN, 2)

    def on_uranus(self):
        return round(self.on_earth() / self.URANUS, 2)

    def on_neptune(self):
        return round(self.on_earth() / self.NEPTUNE, 2)
