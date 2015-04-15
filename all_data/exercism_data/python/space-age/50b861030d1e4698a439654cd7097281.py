class SpaceAge(object):
    def __init__(self, seconds):
        self.seconds = seconds
        self.SECONDS_IN_EARTH_YEAR = 31557600.0
        
    def seconds_to_years(self, period):
        return round(self.seconds / self.SECONDS_IN_EARTH_YEAR / period, 2)
    
    def on_mercury(self):
        return self.seconds_to_years(0.2408467)
        
    def on_venus(self):
        return self.seconds_to_years(0.61519726)
       
    def on_earth(self):
        return self.seconds_to_years(1)

    def on_mars(self):
        return self.seconds_to_years(1.8808158)
       
    def on_jupiter(self):
        return self.seconds_to_years(11.862615)
       
    def on_saturn(self):
        return self.seconds_to_years(29.447498)

    def on_uranus(self):
        return self.seconds_to_years(84.016846)
       
    def on_neptune(self):
        return self.seconds_to_years(164.79132)
       
               

        
