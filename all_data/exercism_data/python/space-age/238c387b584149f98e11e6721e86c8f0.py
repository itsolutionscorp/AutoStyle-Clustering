from math import ceil

earthYearInSecs = 31557600.0
orbitsInEarthYears = {'mercury': 0.2408467, 'venus': 0.61519726, 'earth': 1.0,
                      'mars': 1.8808158, 'jupiter': 11.862615, 'saturn': 29.447498,
                      'uranus': 84.016846, 'neptune': 164.79132 }

class SpaceAge:
    def __init__(self, age):
        self.seconds = age

    def __getattr__(self, name):
        s = name.split('_')
        if len(s) == 2 and s[0] == 'on' and s[1] in orbitsInEarthYears:
            return lambda: round(self.seconds / earthYearInSecs / orbitsInEarthYears[s[1]], 2)
