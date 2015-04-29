# Space Age

class SpaceAge(object):
    def __init__(self, seconds):
        self.seconds = seconds
        self.orbital_periods = {'Earth': 1, 'Mercury': 0.2408467,
                                'Venus': 0.61519726, 'Mars': 1.8808158,
                                'Jupiter': 11.862615, 'Saturn': 29.447498,
                                'Uranus': 84.016846, 'Neptune': 164.79132}
        self.earth_year_seconds = 31557600
        
    def on_earth(self):
        planet_year = self.earth_year_seconds * self.orbital_periods['Earth']
        
        return round(self.seconds / planet_year, 2)
        
    def on_mercury(self):
        planet_year = self.earth_year_seconds * self.orbital_periods['Mercury']
        
        return round(self.seconds / planet_year, 2)
        
    def on_venus(self):
        planet_year = self.earth_year_seconds * self.orbital_periods['Venus']
        
        return round(self.seconds / planet_year, 2)
        
    def on_mars(self):
        planet_year = self.earth_year_seconds * self.orbital_periods['Mars']
        
        return round(self.seconds / planet_year, 2)
        
    def on_jupiter(self):
        planet_year = self.earth_year_seconds * self.orbital_periods['Jupiter']
        
        return round(self.seconds / planet_year, 2)
        
    def on_saturn(self):
        planet_year = self.earth_year_seconds * self.orbital_periods['Saturn']
        
        return round(self.seconds / planet_year, 2)
    
    def on_uranus(self):
        planet_year = self.earth_year_seconds * self.orbital_periods['Uranus']
        
        return round(self.seconds / planet_year, 2)
        
    def on_neptune(self):
        planet_year = self.earth_year_seconds * self.orbital_periods['Neptune']
        
        return round(self.seconds / planet_year, 2)
