class SpaceAge(object):
    _ratios = {"Mercury":0.2408467,
               "Venus":0.61519726,
               "Mars":1.8808158,
               "Jupiter":11.862615,
               "Saturn":29.447498,
               "Uranus":84.016846,
               "Neptune":164.79132
               }
    
    seconds = 0
    earthsecondsperyear = 31557600
    earth_years = 0

    def __init__(self,seconds):
        self.seconds = seconds
        self.earth_years = seconds/self.earthsecondsperyear

    def on_earth(self):
        return round(self.earth_years,2)

    def on_jupiter(self):
        return round(self.earth_years/self._ratios["Jupiter"],2)

    def on_mercury(self):
        return round(self.earth_years/self._ratios["Mercury"],2)

    def on_mars(self):
        return round(self.earth_years/self._ratios["Mars"],2)

    def on_neptune(self):
        return round(self.earth_years/self._ratios["Neptune"],2)

    def on_saturn(self):
        return round(self.earth_years/self._ratios["Saturn"],2)

    def on_uranus(self):
        return round(self.earth_years/self._ratios["Uranus"],2)

    def on_venus(self):
        return round(self.earth_years/self._ratios["Venus"],2)
