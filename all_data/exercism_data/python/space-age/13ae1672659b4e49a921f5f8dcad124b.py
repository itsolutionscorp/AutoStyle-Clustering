from  decimal import *

earthYearInSecs = Decimal(31557600)
orbitsInEarthYears = {'Mercury': Decimal(0.2408467), 'Venus': Decimal(0.61519726), 'Earth': Decimal(1.0),
                      'Mars': Decimal(1.8808158), 'Jupiter': Decimal(11.862615), 'Saturn': Decimal(29.447498),
                      'Uranus': Decimal(84.016846), 'Neptune': Decimal(164.79132) }

class SpaceAge:
    def __init__(self, age):
        self.seconds = Decimal(age)

    def on_mercury(self):
        return self.ageinEarthYears('Mercury')

    def on_venus(self):
        return self.ageinEarthYears('Venus')

    def on_earth(self):
        return self.ageinEarthYears('Earth')

    def on_mars(self):
        return self.ageinEarthYears('Mars')

    def on_jupiter(self):
        return self.ageinEarthYears('Jupiter')

    def on_saturn(self):
        return self.ageinEarthYears('Saturn')

    def on_uranus(self):
        return self.ageinEarthYears('Uranus')

    def on_neptune(self):
        return self.ageinEarthYears('Neptune')

    def ageinEarthYears(self, planet):
        return float((self.seconds / earthYearInSecs / orbitsInEarthYears[planet]).quantize(Decimal('.01')))
