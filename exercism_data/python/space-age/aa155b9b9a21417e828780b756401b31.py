class SpaceAge(object):
    ORBITAL_PERIODS = {"earth"   : 31557600 * 1,
                       "mercury" : 31557600 * .2408467, 
                       "venus"   : 31557600 * .61519726,
                       "mars"    : 31557600 * 1.8808158,
                       "jupiter" : 31557600 * 11.862615,
                       "saturn"  : 31557600 * 29.447498,
                       "uranus"  : 31557600 * 84.016846,
                       "neptune" : 31557600 * 164.79132}

    def __init__(self, seconds):
        """Gives age in orbital periods, given a number of seconds alive.

Usage: SpaceAge(some_seconds).on_earth()"""

        self.seconds = seconds

    def on_earth(self):
        return round(self.seconds / self.ORBITAL_PERIODS["earth"],2)
    def on_mercury(self):
        return round(self.seconds / self.ORBITAL_PERIODS["mercury"],2)
    def on_venus(self):
        return round(self.seconds / self.ORBITAL_PERIODS["venus"],2)
    def on_mars(self):
        return round(self.seconds / self.ORBITAL_PERIODS["mars"],2)
    def on_jupiter(self):
        return round(self.seconds / self.ORBITAL_PERIODS["jupiter"],2)
    def on_saturn(self):
        return round(self.seconds / self.ORBITAL_PERIODS["saturn"],2)
    def on_uranus(self):
        return round(self.seconds / self.ORBITAL_PERIODS["uranus"],2)
    def on_neptune(self):
        return round(self.seconds / self.ORBITAL_PERIODS["neptune"],2)
