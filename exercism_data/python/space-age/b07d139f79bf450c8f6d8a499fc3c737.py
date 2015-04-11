class SpaceAge():
    
    EARTH_YEAR = 31557600.0
    
    year = {'Earth': 1, 'Mercury': 0.2408467, 'Venus': 0.61519726,	        \
            'Mars': 1.8808158,   'Jupiter': 11.862615, 'Saturn': 29.447498,	\
            'Uranus': 84.016846, 'Neptune': 164.79132}
    
    def __init__(self,seconds=0):
        self.seconds = seconds
        
    def calc_year(self, planet):
        return round(self.seconds / self.EARTH_YEAR / self.year[planet], 2)
        
    def on_earth(self):     return self.calc_year('Earth')
    def on_mercury(self):   return self.calc_year('Mercury')
    def on_venus(self):     return self.calc_year('Venus')
    def on_mars(self):      return self.calc_year('Mars')
    def on_jupiter(self):   return self.calc_year('Jupiter')
    def on_saturn(self):    return self.calc_year('Saturn')
    def on_uranus(self):    return self.calc_year('Uranus')
    def on_neptune(self):   return self.calc_year('Neptune')
