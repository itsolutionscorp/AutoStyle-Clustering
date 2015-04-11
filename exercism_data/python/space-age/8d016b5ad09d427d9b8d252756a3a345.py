convert = lambda sec, ratio: sec/31557600. / ratio

class SpaceAge:
    
    
    def __init__(self, seconds):
        self.seconds = seconds

        
    def on_mercury(self):
        return round(convert(self.seconds, 0.2408467), 2)
    
    def on_venus(self):
        return round(convert(self.seconds, 0.61519726), 2)
    
    def on_earth(self):
        return round(convert(self.seconds, 1.), 2)
    
    def on_mars(self):
        return round(convert(self.seconds, 1.8808158), 2)
    
    def on_jupiter(self):
        return round(convert(self.seconds, 11.862615), 2)
    
    def on_saturn(self):
        return round(convert(self.seconds, 29.447498), 2)
    
    def on_uranus(self):
        return round(convert(self.seconds, 84.016846), 2)
    
    def on_neptune(self):
        return round(convert(self.seconds, 164.79132), 2)
