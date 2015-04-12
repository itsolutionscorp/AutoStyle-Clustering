class SpaceAge:
    def __init__(self, seconds):
        self.seconds = seconds
    def on_earth(self):
        return float("{0:.2f}".format(self.seconds / 31557600.0))
    def on_mercury(self):
        return float("{0:.2f}".format(self.seconds / (31557600.0*0.2408467)))
    def on_venus(self):
        return float("{0:.2f}".format(self.seconds / (31557600.0*0.61519726)))
    def on_mars(self):
        return float("{0:.2f}".format(self.seconds / (31557600.0*1.8808158)))
    def on_jupiter(self):
        return float("{0:.2f}".format(self.seconds / (31557600.0*11.862615)))
    def on_saturn(self):
        return float("{0:.2f}".format(self.seconds / (31557600.0*29.447498)))
    def on_uranus(self):
        return float("{0:.2f}".format(self.seconds / (31557600.0*84.016846)))
    def on_neptune(self):
        return float("{0:.2f}".format(self.seconds / (31557600.0*164.79132)))