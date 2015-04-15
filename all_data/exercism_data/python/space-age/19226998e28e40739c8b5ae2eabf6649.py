class SpaceAge:
    earth = 31557600
    orbital = [
        0.2408467, 0.61519726, 1.0000000, 1.8808158, 11.862615, 29.4474980, 84.016846, 164.79132
    ]
    
    def __init__(self, time):
        self.seconds = time
    
    def on_mercury(self):
        year = self.earth * self.orbital[0]
        return round(self.seconds/year, 2)
    
    def on_venus(self):
        year = self.earth * self.orbital[1]
        return round(self.seconds/year, 2)
    
    def on_earth(self):
        year = self.earth * self.orbital[2]
        return round(self.seconds/year, 2)
    
    def on_mars(self):
        year = self.earth * self.orbital[3]
        return round(self.seconds/year, 2)
    
    def on_jupiter(self):
        year = self.earth * self.orbital[4]
        return round(self.seconds/year, 2)
    
    def on_saturn(self):
        year = self.earth * self.orbital[5]
        return round(self.seconds/year, 2)
    
    def on_uranus(self):
        year = self.earth * self.orbital[6]
        return round(self.seconds/year, 2)
    
    def on_neptune(self):
        year = self.earth * self.orbital[7]
        return round(self.seconds/year, 2)
