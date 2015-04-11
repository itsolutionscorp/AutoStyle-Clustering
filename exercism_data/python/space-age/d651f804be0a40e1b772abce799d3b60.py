__author__ = 'jeffmarkey'
from decimal import *

class SpaceAge:

    def __init__(self, seconds):
        self.seconds = seconds
        self.earth_year = 31557600.0

    def on_earth(self):
        return float(format(float(float(self.seconds) / self.earth_year), '.2f'))

    def on_mercury(self):
        return float(format(float(float(self.seconds) / float(self.earth_year * 0.2408467)), '.2f'))

    def on_venus(self):
        return float(format(float(float(self.seconds) / float(self.earth_year * 0.61519726)), '.2f'))

    def on_jupiter(self):
        return float(format(float(float(self.seconds) / float(self.earth_year * 11.862615)), '.2f'))

    def on_mars(self):
        return float(format(float(float(self.seconds) / float(self.earth_year * 1.8808158)), '.2f'))

    def on_neptune(self):
        return float(format(float(float(self.seconds) / float(self.earth_year * 164.79132)), '.2f'))

    def on_saturn(self):
        return float(format(float(float(self.seconds) / float(self.earth_year * 29.447498)), '.2f'))

    def on_uranus(self):
        return float(format(float(float(self.seconds) / float(self.earth_year * 84.016846)), '.2f'))
