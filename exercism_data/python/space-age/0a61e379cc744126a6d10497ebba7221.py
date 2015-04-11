earth_year_ratio = {
    "earth":   1,
    "mercury": 0.2408467,
    "venus":   0.6159726,
    "mars":    1.8808158,
    "jupiter": 11.862615,
    "saturn":  29.447498,
    "uranus":  84.016846,
    "neptune": 164.79132,
}

SECONDS_PER_EARTH_YEAR = 365.25 * 60 * 60 * 24

class SpaceAge():
    def __init__(self, seconds):
        self.seconds = seconds
        self.convert = lambda planet: round(
            self.seconds / 
            (SECONDS_PER_EARTH_YEAR * earth_year_ratio[planet]), 2)
    
    def on_earth(self):
        return self.convert("earth")

    
    def on_mercury(self):
        return self.convert("mercury")

    
    def on_venus(self):
        return self.convert("venus")


    def on_mars(self):
        return self.convert("mars")


    def on_jupiter(self):
        return self.convert("jupiter")


    def on_saturn(self):
        return self.convert("saturn")    


    def on_uranus(self):
        return self.convert("uranus")


    def on_neptune(self):
        return self.convert("neptune")
