earth_year_seconds = 31557600
planet_year_dictionary = {"mercury": 0.2408467,
                          "venus": 0.61519726,
                          "earth": 1.0,
                          "mars":  1.8808158,
                          "jupiter":  11.862615,
                          "saturn":  29.447498,
                          "uranus":  84.016846,
                          "neptune": 164.79132}

class SpaceAge(object):
    """Returns the age of a person on various planets"""
    
    def __init__(self, seconds):
        self.seconds = seconds        
        for planet in planet_year_dictionary:
            self.add_method(planet)

    def add_method(self, planet):
        """Creates functions to return the person's age on each planet"""
        self.__setattr__('on_' + planet, lambda: self.age_on_planet(planet))
            
    def age_on_planet(self, planet):
        """Calculates the person's age on a given planet"""
        age = self.seconds/earth_year_seconds/planet_year_dictionary[planet]
        return round(age,2)
