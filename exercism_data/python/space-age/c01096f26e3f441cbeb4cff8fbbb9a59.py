_earth_years_sec = 31557600

_planet_mapping = {'on_mercury':0.2408467, 'on_venus':0.61519726, 'on_mars':1.8808158,
                  'on_jupiter':11.862615, 'on_saturn':29.447498, 'on_uranus':84.016846,
                  'on_neptune':164.79132}

class SpaceAge():
        def __init__(self, seconds):
                self.seconds = seconds
                self.earth_age_years = (self.seconds / _earth_years_sec)
                
        
        def on_earth(self):
                return round(self.earth_age_years, 2)

        
        def on_mercury(self):
                return round(self.earth_age_years / _planet_mapping['on_mercury'], 2)

        
        def on_venus(self):
                return round(self.earth_age_years / _planet_mapping['on_venus'], 2)

        
        def on_mars(self):
                return round(self.earth_age_years / _planet_mapping['on_mars'], 2)
        

        def on_jupiter(self):
                return round(self.earth_age_years / _planet_mapping['on_jupiter'], 2)

        
        def on_saturn(self):
                return round(self.earth_age_years / _planet_mapping['on_saturn'], 2)

        
        def on_uranus(self):
                return round(self.earth_age_years / _planet_mapping['on_uranus'], 2)


        def on_neptune(self):
                return round(self.earth_age_years / _planet_mapping['on_neptune'], 2)
