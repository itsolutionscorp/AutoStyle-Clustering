class SpaceAge(object):
    #calculate age on planets (surrounding Sol) relative to Earth years (in sec)
    earth_year_sec = 31557600.0

    def __init__(self, seconds):
    #init attributes for relative age determined by functions & ratios below
        self.seconds = seconds
        self.ratio = dict(
            on_mercury = 0.2408467, on_venus = 0.61519726, on_earth = 1.0, 
            on_mars = 1.8808158, on_jupiter = 11.862615, on_saturn = 29.447498,
            on_uranus  = 84.016846, on_neptune = 164.79132
            )
            
        for planet in self.ratio.keys():
            setattr(self, planet, self.on_planet(planet))

    def on_planet(self, planet):
    #Returns function converting seconds into years on planet relative to Earth
        return lambda: round(
            (self.seconds/self.earth_year_sec)/self.ratio[planet], 2)
