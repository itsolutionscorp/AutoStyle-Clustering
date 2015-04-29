class SpaceAge:
    def __init__(self, age):
        self.seconds = age
        self.on_earth   = self.on_planet(1.0)
        self.on_mercury = self.on_planet(0.2408467)
        self.on_venus   = self.on_planet(0.61519726)
        self.on_mars    = self.on_planet(1.8808158)
        self.on_jupiter = self.on_planet(11.862615)
        self.on_saturn  = self.on_planet(29.447498)
        self.on_uranus  = self.on_planet(84.016846)
        self.on_neptune = self.on_planet(164.79132)

    def on_planet(self, coefficient):
        earth_year = 31557600
        def calc():
            return round(self.seconds / earth_year / coefficient, 2)
                
        return calc
