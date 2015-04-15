#!/usr/bin/env python

PLANETS = {
    "on_earth": 31557600.0,
    "on_mercury": 31557600.0 * 0.2408467,
    "on_venus": 31557600.0 * 0.61519726,
    "on_mars": 31557600.0 * 1.8808158,
    "on_jupiter": 31557600.0 * 11.862615,
    "on_saturn": 31557600.0 * 29.447498,
    "on_uranus": 31557600.0 * 84.016846,
    "on_neptune": 31557600.0 * 164.79132
}

class SpaceAge(object):
    
    def __init__(self, seconds):
        self.seconds = seconds
        
        if not isinstance(seconds, (int, long, float)):
            raise ValueError("Invalid input type {}".format(type(seconds)))
    
        for k, v in PLANETS.iteritems():
            func = lambda seconds=seconds, v=v: round(seconds / v, 2)
            setattr(self, k, func)
    
