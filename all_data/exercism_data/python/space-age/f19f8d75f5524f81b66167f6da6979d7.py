earth_year_seconds = 31557600
planet_year_dictionary = {"Mercury": 0.2408467,
                          "Venus": 0.61519726,
                          "Earth": 1.0,
                          "Mars":  1.8808158,
                          "Jupiter":  11.862615,
                          "Saturn":  29.447498,
                          "Uranus":  84.016846,
                          "Neptune": 164.79132}

class SpaceAge(object):

    def __init__(self, seconds):
        self.seconds = seconds

    def age_on_planet(self, planet):
        age = self.seconds/earth_year_seconds/planet_year_dictionary[planet]
        return round(age,2)

    def on_mercury(self):
        return self.age_on_planet("Mercury")

    def on_venus(self):
        return self.age_on_planet("Venus")

    def on_earth(self):
        return self.age_on_planet("Earth")

    def on_mars(self):
        return self.age_on_planet("Mars")

    def on_jupiter(self):
        return self.age_on_planet("Jupiter")

    def on_saturn(self):
        return self.age_on_planet("Saturn")

    def on_uranus(self):
        return self.age_on_planet("Uranus")

    def on_neptune(self):
        return self.age_on_planet("Neptune")
