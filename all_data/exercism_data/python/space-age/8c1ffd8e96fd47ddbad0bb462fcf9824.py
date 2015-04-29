#!/usr/bin/env python2
# -*- coding: utf-8 -*-

class SpaceAge(object):

    def __init__(self, sec):
        self.__seconds = float(sec)
        self.__earth_age = self.__seconds/31557600.0

    @property
    def seconds(self):
        return self.__seconds

    def on_earth(self):
        """Earth: orbital period 365.25 Earth days, or 31557600 seconds
        """
        return round(self.__earth_age, 2)

    def on_mercury(self):
        """Mercury: orbital period 0.2408467 Earth years
        """
        return round(self.__earth_age/0.2408467, 2)

    def on_venus(self):
        """Venus: orbital period 0.61519726 Earth years
        """
        return round(self.__earth_age/0.61519726, 2)

    def on_mars(self):
        """Mars: orbital period 1.8808158 Earth years
        """
        return round(self.__earth_age/1.8808158, 2)

    def on_jupiter(self):
        """Jupiter: orbital period 11.862615 Earth years
        """
        return round(self.__earth_age/11.862615, 2)

    def on_saturn(self):
        """Saturn: orbital period 29.447498 Earth years
        """
        return round(self.__earth_age/29.447498, 2)

    def on_uranus(self):
        """Uranus: orbital period 84.016846 Earth years
        """
        return round(self.__earth_age/84.016846, 2)

    def on_neptune(self):
        """Neptune: orbital period 164.79132 Earth years
        """
        return round(self.__earth_age/164.79132, 2)

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
