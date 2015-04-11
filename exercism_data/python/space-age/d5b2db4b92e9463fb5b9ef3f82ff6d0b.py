#!/usr/bin/env python

EARTH_YEAR_IN_SECONDS = 31557600.0
MERCURY_YEAR_IN_EARTH_YEARS = 0.2408467
VENUS_YEAR_IN_EARTH_YEARS = 0.61519726
MARS_YEAR_IN_EARTH_YEARS = 1.8808158
JUPITER_YEAR_IN_EARTH_YEARS = 11.862615
SATURN_YEAR_IN_EARTH_YEARS = 29.447498
URANUS_YEAR_IN_EARTH_YEARS = 84.016846
NEPTUNE_YEAR_IN_EARTH_YEARS = 164.79132

class SpaceAge:
    def __init__(self, seconds):
        self.seconds = seconds
    
    def on_earth(self):
        return round(self.seconds / EARTH_YEAR_IN_SECONDS, 2)
    
    def on_mercury(self):
        return round(self.seconds / EARTH_YEAR_IN_SECONDS / MERCURY_YEAR_IN_EARTH_YEARS, 2)
    
    def on_venus(self):
        return round(self.seconds / EARTH_YEAR_IN_SECONDS / VENUS_YEAR_IN_EARTH_YEARS, 2)
    
    def on_mars(self):
        return round(self.seconds / EARTH_YEAR_IN_SECONDS / MARS_YEAR_IN_EARTH_YEARS, 2)
    
    def on_jupiter(self):
        return round(self.seconds / EARTH_YEAR_IN_SECONDS / JUPITER_YEAR_IN_EARTH_YEARS, 2)
    
    def on_saturn(self):
        return round(self.seconds / EARTH_YEAR_IN_SECONDS / SATURN_YEAR_IN_EARTH_YEARS, 2)
    
    def on_uranus(self):
        return round(self.seconds / EARTH_YEAR_IN_SECONDS / URANUS_YEAR_IN_EARTH_YEARS, 2)
    
    def on_neptune(self):
        return round(self.seconds / EARTH_YEAR_IN_SECONDS / NEPTUNE_YEAR_IN_EARTH_YEARS, 2)
