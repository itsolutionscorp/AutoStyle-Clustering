class SpaceAge:
    def __init__(self,number):

        self.seconds = number
        self.earth_year = 31557600.00
        self.mercury = 0.2408467
        self.venus = 0.61519726
        self.mars = 1.8808158
        self.jupiter = 11.862615
        self.saturn = 29.447498
        self.uranus = 84.016846
        self.neptune = 164.79132
        self.earth = number/self.earth_year

    def on_earth(self):
        return float("{0:.2f}".format(self.earth))

    def on_mercury(self):
        return float("{0:.2f}".format(self.earth/self.mercury))

    def on_venus(self):
        return float("{0:.2f}".format(self.earth/self.venus))

    def on_mars(self):
        return float("{0:.2f}".format(self.earth/self.mars))

    def on_jupiter(self):
        return float("{0:.2f}".format(self.earth/self.jupiter))

    def on_saturn(self):
        return float("{0:.2f}".format(self.earth/self.saturn))

    def on_uranus(self):
        return float("{0:.2f}".format(self.earth/self.uranus))

    def on_neptune(self):
        return float("{0:.2f}".format(self.earth/self.neptune))
