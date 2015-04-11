class SpaceAge():
    orbits = (0.2408467,
              0.61519726,
              1.0,
              1.8808158,
              11.862615,
              29.447498,
              84.016846,
              164.79132)

    scalar = 31557600.0
    
    def __init__(self, age):
        self.seconds = self.age = age
        
        self.on_mercury = lambda: round(self.age/self.scalar/self.orbits[0],2)
        self.on_venus = lambda: round(self.age/self.scalar/self.orbits[1],2)
        self.on_earth = lambda: round(self.age/self.scalar/self.orbits[2],2)
        self.on_mars = lambda: round(self.age/self.scalar/self.orbits[3],2)
        self.on_jupiter = lambda: round(self.age/self.scalar/self.orbits[4],2)
        self.on_saturn = lambda: round(self.age/self.scalar/self.orbits[5],2)
        self.on_uranus = lambda: round(self.age/self.scalar/self.orbits[6],2)
        self.on_neptune = lambda: round(self.age/self.scalar/self.orbits[7],2)
