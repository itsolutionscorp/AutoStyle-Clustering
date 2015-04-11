class SpaceAge(object):
    
    
    def __init__(self, age):
        self.seconds = age
        self.earthyear = 31557600.00
        self.yearkey = {
            'Mercury': 0.2408467,
            'Venus': 0.61519726,
            'Earth': 1.00,
            'Mars': 1.8808158,
            'Jupiter': 11.862615,
            'Saturn': 29.447498,
            'Uranus': 84.016846,
            'Neptune': 164.79132}

    def returnnum(self, i):
        return round((self.seconds / self.earthyear) / self.yearkey[i], 2)

    def on_earth(self):
        return self.returnnum('Earth')
    def on_mercury(self):
        return self.returnnum('Mercury')
    def on_venus(self):
        return self.returnnum('Venus')
    def on_mars(self):
        return self.returnnum('Mars')
    def on_jupiter(self):
        return self.returnnum('Jupiter')
    def on_saturn(self):
        return self.returnnum('Saturn')
    def on_uranus(self):
        return self.returnnum('Uranus')
    def on_neptune(self):
        return self.returnnum('Neptune')
    
