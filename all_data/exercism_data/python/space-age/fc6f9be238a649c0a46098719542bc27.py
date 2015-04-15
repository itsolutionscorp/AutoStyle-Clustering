#!/usr/bin/env python

"""
Earth:   orbital period 365.25 Earth days, or 31557600 seconds
Mercury: orbital period 0.2408467 Earth years
Venus:   orbital period 0.61519726 Earth years
Mars:    orbital period 1.8808158 Earth years
Jupiter: orbital period 11.862615 Earth years
Saturn:  orbital period 29.447498 Earth years
Uranus:  orbital period 84.016846 Earth years
Neptune: orbital period 164.79132 Earth years
"""

class SpaceAge(object):
    
    def __init__(self, secs):
        self.seconds = secs
        if not isinstance(secs, (int, long, float)):
            raise ValueError("Invalid input type {}".format(type(secs)))

    def on_earth(self):
        return eval("%.2f" % (self.seconds / 31557600.0))
    
    def on_mercury(self):
        return eval("%.2f" % (self.on_earth() / 0.2408467))
        
    def on_venus(self):
        return eval("%.2f" % (self.on_earth() / 0.61519726))
        
    def on_mars(self):
        return eval("%.2f" % (self.on_earth() / 1.8808158))
        
    def on_jupiter(self):
        return eval("%.2f" % (self.on_earth() / 11.862615))
        
    def on_saturn(self):
        return eval("%.2f" % (self.on_earth() / 29.447498))
        
    def on_uranus(self):
        return eval("%.2f" % (self.on_earth() / 84.016846))
        
    def on_neptune(self):
        return eval("%.2f" % (self.on_earth() / 164.79132))
