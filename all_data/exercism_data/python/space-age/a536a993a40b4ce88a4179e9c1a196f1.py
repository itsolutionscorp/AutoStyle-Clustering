import sys

class SpaceAge:    

    def __init__(self, age):
        self.seconds = age
        self.earth_year = float(age) / 31557600
        self.mapToEarth = {
        'Mercury': 0.2408467,
        'Venus': 0.61519726,
        'Mars': 1.8808158,
        'Jupiter': 11.862615,
        'Saturn': 29.447498,
        'Uranus': 84.016846,
        'Neptune': 164.79132
        }
        
    def on_earth(self):
        return round(self.earth_year, 2)

    def on_mercury(self):
        function_name = sys._getframe().f_code.co_name
        function_name = function_name[3:].capitalize()
        return round(self.earth_year / self.mapToEarth[function_name], 2)        

    def on_venus(self):
        function_name = sys._getframe().f_code.co_name
        function_name = function_name[3:].capitalize()
        return round(self.earth_year / self.mapToEarth[function_name], 2)        

    def on_mars(self):
        function_name = sys._getframe().f_code.co_name
        function_name = function_name[3:].capitalize()
        return round(self.earth_year / self.mapToEarth[function_name], 2)    

    def on_jupiter(self):
        function_name = sys._getframe().f_code.co_name
        function_name = function_name[3:].capitalize()
        return round(self.earth_year / self.mapToEarth[function_name], 2)        

    def on_saturn(self):
        function_name = sys._getframe().f_code.co_name
        function_name = function_name[3:].capitalize()
        return round(self.earth_year / self.mapToEarth[function_name], 2)        

    def on_uranus(self):
        function_name = sys._getframe().f_code.co_name
        function_name = function_name[3:].capitalize()
        return round(self.earth_year / self.mapToEarth[function_name], 2)        

    def on_neptune(self):
        function_name = sys._getframe().f_code.co_name
        function_name = function_name[3:].capitalize()
        return round(self.earth_year / self.mapToEarth[function_name], 2)                
