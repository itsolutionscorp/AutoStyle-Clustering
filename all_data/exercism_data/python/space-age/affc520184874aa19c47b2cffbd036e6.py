__author__ = 'emiller42'

from decimal import Decimal


class SpaceAge:
    EARTH = Decimal('31557600')
    MERCURY = EARTH * Decimal('0.2408467')
    VENUS = EARTH * Decimal('0.61519726')
    MARS = EARTH * Decimal('1.8808158')
    JUPITER = EARTH * Decimal('11.862615')
    SATURN = EARTH * Decimal('29.447498')
    URANUS = EARTH * Decimal('84.016846')
    NEPTUNE = EARTH * Decimal('164.79132')

    def __init__(self, seconds):
        self.seconds = Decimal(seconds)

    def on_earth(self):
        return round(self.seconds / SpaceAge.EARTH, 2)

    def on_mercury(self):
        return round(self.seconds / SpaceAge.MERCURY, 2)

    def on_venus(self):
        return round(self.seconds / SpaceAge.VENUS, 2)

    def on_mars(self):
        return round(self.seconds / SpaceAge.MARS, 2)

    def on_jupiter(self):
        return round(self.seconds / SpaceAge.JUPITER, 2)

    def on_saturn(self):
        return round(self.seconds / SpaceAge.SATURN, 2)

    def on_uranus(self):
        return round(self.seconds / SpaceAge.URANUS, 2)

    def on_neptune(self):
        return round(self.seconds / SpaceAge.NEPTUNE, 2)
