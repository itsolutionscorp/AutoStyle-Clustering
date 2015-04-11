from datetime import timedelta

class SpaceAge(object):
    spacetime_dict = {'mercury': 0.2408467,
                      'venus': 0.61519726,
                      'mars': 1.8808158,
                      'jupiter': 11.862615,
                      'saturn': 29.447498,
                      'uranus': 84.016846,
                      'neptune': 164.79132}

    def __init__(self, age):
        self.age = timedelta(seconds=age)
        self.seconds = self.age.total_seconds()

    def on_earth(self):
        return round(self.age.days/365.25, 2)

    def on_mercury(self):
        return round(self.seconds/(31557600*self.spacetime_dict['mercury']), 2)

    def on_venus(self):
        return round(self.age.days/(365.25*self.spacetime_dict['venus']), 2)

    def on_mars(self):
        return round(self.age.days/(365.25*self.spacetime_dict['mars']), 2)

    def on_jupiter(self):
        return round(self.age.days/(365.25*self.spacetime_dict['jupiter']), 2)

    def on_saturn(self):
        return round(self.age.days/(365.25*self.spacetime_dict['saturn']), 2)

    def on_uranus(self):
        return round(self.age.days/(365.25*self.spacetime_dict['uranus']), 2)

    def on_neptune(self):
        return round(self.age.days/(365.25*self.spacetime_dict['neptune']), 2)
