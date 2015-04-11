class SpaceAge:

    def __init__(self, seconds):
        self.seconds = seconds

        self.on_earth   = self.make_converter(1)
        self.on_mercury = self.make_converter(0.2408467)
        self.on_venus   = self.make_converter(0.61519726)
        self.on_mars    = self.make_converter(1.8808158)
        self.on_jupiter = self.make_converter(11.862615)
        self.on_saturn  = self.make_converter(29.447498)
        self.on_uranus  = self.make_converter(84.016846)
        self.on_neptune = self.make_converter(164.79132)

    def make_converter(self, relative):
        def converter():
            return round(self.seconds / (31557600 * relative), 2)

        return converter
