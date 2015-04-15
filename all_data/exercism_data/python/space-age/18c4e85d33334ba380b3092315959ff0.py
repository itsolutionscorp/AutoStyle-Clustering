class SpaceAge(object):

    def __init__(self,seconds):
        self.seconds = seconds
        self.multipliers = [0.2408467,
                            0.61519726,
                            1.0,
                            1.8808158,
                            11.862615,
                            29.447498,
                            84.016846,
                            164.79132]
        self.EARTH_YEAR = 31557600.0


    def get_age(self,index):
        return float("{0:.2f}".format(self.seconds/self.EARTH_YEAR/self.multipliers[index]))
        

    def on_mercury(self):
        return self.get_age(0)
    def on_venus(self):
        return self.get_age(1)
    def on_earth(self):
        return self.get_age(2)
    def on_mars(self):
        return self.get_age(3)
    def on_jupiter(self):
        return self.get_age(4)
    def on_saturn(self):
        return self.get_age(5)
    def on_uranus(self):
        return self.get_age(6)
    def on_neptune(self):
        return self.get_age(7)
