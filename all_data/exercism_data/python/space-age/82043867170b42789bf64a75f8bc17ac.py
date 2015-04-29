class SpaceAge:
    orbits = {
        'Earth': 365.25,
        'Mercury': 0.2408467,
        'Venus': 0.61519726,
        'Mars': 1.8808158,
        'Jupiter': 11.862615,
        'Saturn': 29.447498,
        'Uranus': 84.016846,
        'Neptune': 164.79132
    }
    
    def __init__(self, age):
        self.seconds = age

    def orbitSecond(self, planet):
        return self.orbits[planet] * self.orbits['Earth'] * 24 * 60**2

    def on_earth(self):
        return round(self.seconds / (self.orbits['Earth'] * 24 * 60**2), 2)

    def on_mercury(self):
        return round(self.seconds / self.orbitSecond('Mercury'), 2)

    def on_venus(self):
        return round(self.seconds / self.orbitSecond('Venus'), 2)

    def on_mars(self):
        return round(self.seconds / self.orbitSecond('Mars'), 2)

    def on_jupiter(self):
        return round(self.seconds / self.orbitSecond('Jupiter'), 2)

    def on_saturn(self):
        return round(self.seconds / self.orbitSecond('Saturn'), 2)

    def on_uranus(self):
        return round(self.seconds / self.orbitSecond('Uranus'), 2)

    def on_neptune(self):
        return round(self.seconds / self.orbitSecond('Neptune'), 2)
# age = SpaceAge(2134835688)
# print age.seconds
# print age.on_earth()
# print age.on_mercury()
