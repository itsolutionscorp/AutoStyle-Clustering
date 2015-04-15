class SpaceAge():
    
    EARTH_YEAR = 31557600.0
    
    def __init__(self,seconds=0):
        self.seconds = seconds
        
    def calc_year(self, planet, ratio):
        return round(self.seconds / self.EARTH_YEAR / ratio, 2)
        
    def on_earth(self)  :   return self.calc_year('Earth'   ,   1           )
    def on_mercury(self):   return self.calc_year('Mercury' ,   0.2408467   )
    def on_venus(self)  :   return self.calc_year('Venus'   ,   0.61519726  )
    def on_mars(self)   :   return self.calc_year('Mars'    ,   1.8808158   )
    def on_jupiter(self):   return self.calc_year('Jupiter' ,   11.862615   )
    def on_saturn(self) :   return self.calc_year('Saturn'  ,   29.447498   )
    def on_uranus(self) :   return self.calc_year('Uranus'  ,   84.016846   )
    def on_neptune(self):   return self.calc_year('Neptune' ,   164.79132   )
