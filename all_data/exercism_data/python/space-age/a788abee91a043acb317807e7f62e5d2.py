class SpaceAge:
    EarthYear = 31557600
    
    def __init__(self,AgeInSeconds):
        self.AgeInSeconds = AgeInSeconds
        
    @property
    def seconds(self):
        return float(self.AgeInSeconds)
    
    def on_earth(self):
        return float(format(self.AgeInSeconds/self.EarthYear,".2f"))
        
    def on_mercury(self):
        return float(format(self.AgeInSeconds/(self.EarthYear*0.2408467),".2f"))
        
    def on_venus(self):
        return float(format(self.AgeInSeconds/(self.EarthYear*0.61519726),".2f"))
        
    def on_mars(self):
        return float(format(self.AgeInSeconds/(self.EarthYear*1.8808158),".2f"))
        
    def on_jupiter(self):
        return float(format(self.AgeInSeconds/(self.EarthYear*11.862615),".2f"))
        
    def on_saturn(self):
        return float(format(self.AgeInSeconds/(self.EarthYear*29.447498),".2f"))
        
    def on_uranus(self):
        return float(format(self.AgeInSeconds/(self.EarthYear*84.016846),".2f"))
        
    def on_neptune(self):
        return float(format(self.AgeInSeconds/(self.EarthYear*164.79132),".2f"))
        
    
