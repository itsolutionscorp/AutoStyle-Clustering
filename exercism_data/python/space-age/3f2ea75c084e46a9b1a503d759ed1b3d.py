from datetime import timedelta

__author__ = 'jimblackler'


class SpaceAge(object):
    def __init__(self, seconds):
        self.age = timedelta(seconds=seconds)

    @property
    def seconds(self):
        return self.age.total_seconds()

    def on_earth_unrounded(self):
        return self.age.days / 365.25

    def on_earth(self):
        return round(self.on_earth_unrounded(), 2)

    def on_mercury(self):
        return round(self.on_earth_unrounded() / 0.2408467, 2)

    def on_venus(self):
        return round(self.on_earth_unrounded() / 0.61519726, 2)

    def on_mars(self):
        return round(self.on_earth_unrounded() / 1.8808158, 2)

    def on_jupiter(self):
        return round(self.on_earth_unrounded() / 11.862615, 2)

    def on_saturn(self):
        return round(self.on_earth_unrounded() / 29.447498, 2)

    def on_uranus(self):
        return round(self.on_earth_unrounded() / 84.016846, 2)

    def on_neptune(self):
        return round(self.on_earth_unrounded() / 164.79132, 2)
