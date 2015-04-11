class SpaceAge(object):
    #calculate age on planets (surrounding Sol) relative to Earth years (in sec)
    earth_year_sec = 31557600.0

    def __init__(self, seconds):
    #init attributes for relative age determined by functions & ratios below
        self.seconds = seconds
        self.on_earth = self.on_planet(1.0)
        self.on_mercury = self.on_planet(0.2408467)
        self.on_venus = self.on_planet(0.61519726)
        self.on_mars = self.on_planet(1.8808158)
        self.on_jupiter = self.on_planet(11.862615)
        self.on_saturn = self.on_planet(29.447498)
        self.on_uranus = self.on_planet(84.016846)
        self.on_neptune = self.on_planet(164.79132)

    def on_planet(self, ratio_to_earth):
    #Returns function converting seconds into years on planet relative to Earth
        def sec_to_yr():
        #Function that does conversion
            return round((self.seconds/self.earth_year_sec) / ratio_to_earth, 2)
        return sec_to_yr
