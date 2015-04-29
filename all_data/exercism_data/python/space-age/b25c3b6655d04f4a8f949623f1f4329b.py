from __future__ import division

class SpaceAge:
    def __init__(self, seconds):
        self.seconds = seconds
        self.earth_year = 31557600
        self.year_ratio = {
                'mercury' : 0.2408467,
                'venus'   : 0.61519726,
                'earth'   : 1,
                'mars'    : 1.8808158,
                'jupiter' : 11.862615,
                'saturn'  : 29.447498,
                'uranus'  : 84.016846,
                'neptune' : 164.79132
        }
        self.on_mercury = self.age_on_planet('mercury')
        self.on_venus = self.age_on_planet('venus')
        self.on_earth = self.age_on_planet('earth')
        self.on_mars = self.age_on_planet('mars')
        self.on_jupiter = self.age_on_planet('jupiter')
        self.on_saturn = self.age_on_planet('saturn')
        self.on_uranus = self.age_on_planet('uranus')
        self.on_neptune = self.age_on_planet('neptune')
        
    def age_on_planet(self ,planet):
        def age_function():
            year = self.earth_year * self.year_ratio[planet]
            return round(self.seconds / year,2)
        return age_function
