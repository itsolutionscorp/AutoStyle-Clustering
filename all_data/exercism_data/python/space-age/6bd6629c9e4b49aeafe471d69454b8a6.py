class SpaceAge(object):

    def __init__(self, seconds):
        self.seconds = float(seconds)
        self.earth = self.seconds / 31557600.0
        self.mercury = None
        self.venus = None
        self.mars = None
        self.jupiter = None
        self.saturn = None
        self.uranus = None
        self.neptune = None

    def on_earth(self):
        if not self.earth:
            self.earth = self.seconds / 31557600.0
        return round(self.earth, 2)

    def on_mercury(self):
        if not self.mercury:
            self.mercury = self.earth / .2408467
        return round(self.mercury, 2)

    def on_venus(self):
        if not self.venus:
            self.venus = self.earth / .61519726
        return round(self.venus, 2)

    def on_mars(self):
        if not self.mars:
            self.mars = self.earth / 1.8808158
        return round(self.mars, 2)

    def on_jupiter(self):
        if not self.jupiter:
            self.jupiter = self.earth / 11.862615
        return round(self.jupiter, 2)

    def on_saturn(self):
        if not self.saturn:
            self.saturn = self.earth / 29.447498
        return round(self.saturn, 2)

    def on_uranus(self):
        if not self.uranus:
            self.uranus = self.earth / 84.016846
        return round(self.uranus, 2)

    def on_neptune(self):
        if not self.neptune:
            self.neptune = self.earth / 164.79132
        return round(self.neptune, 2)
