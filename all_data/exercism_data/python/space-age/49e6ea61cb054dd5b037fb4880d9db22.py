# Make Python 2 divisions work like divisions in Python 3
# (e.g. making 2/3 result in a 0.6666 float instead of 0 by default)
from __future__ import division

class SpaceAge(object):

    planets = (
        ( 'earth',   1.0000000  ),
        ( 'mercury', 0.2408467  ),
        ( 'venus',   0.61519726 ),
        ( 'mars',    1.8808158  ),
        ( 'jupiter', 11.862615  ),
        ( 'saturn',  29.447498  ),
        ( 'uranus',  84.016846  ),
        ( 'neptune', 164.79132  )
    )

    def __init__(self, age_in_seconds):
        self.seconds = age_in_seconds
        for planet, orbital in SpaceAge.planets:
            self.__create_method_for_planet(planet, orbital)

    def __create_method_for_planet(self, planet, orbital):
        def compute_space_age():
            earth_years = self.seconds / 31557600
            return round(earth_years / orbital, 2)
        setattr(self, "on_" + planet, compute_space_age) 
