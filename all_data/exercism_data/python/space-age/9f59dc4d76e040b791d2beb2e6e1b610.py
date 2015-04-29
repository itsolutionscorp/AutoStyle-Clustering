#!/usr/bin/python
class SpaceAge(object):
    def __init__(self, age):
        self.seconds = age
        self.planets = dict(zip("mercury venus earth mars jupiter saturn uranus neptune".split(),
            [0.2408467, 0.61519726, 1.0, 1.8808158, 11.862615, 29.447498, 84.016846, 164.79132]))

    def __getattribute__(self, name):
        if name[:3] == "on_":
            return (lambda: round(self.seconds / ((365.25 * 24 * 60 * 60) * self.planets[name[3:]]), 2))
        else:
            return object.__getattribute__(self, name)
