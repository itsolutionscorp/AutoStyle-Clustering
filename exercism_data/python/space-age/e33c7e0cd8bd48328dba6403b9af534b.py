class SpaceAge:

    def __init__(self, seconds=1e6):
        self.seconds = seconds
        self.earth = 365.25

    def _calc(self, orbit):
        return format(self.seconds / (orbit * 24 * 60 * 60), '.2f')

    on_earth = lambda self: self._calc(self.earth)
    on_mercury = lambda self: self._calc(self.earth * 0.2408467)
    on_venus = lambda self: self._calc(self.earth * 0.61519726)
    on_mars = lambda self: self._calc(self.earth * 1.8808158)
    on_jupiter = lambda self: self._calc(self.earth * 11.862615)
    on_saturn = lambda self: self._calc(self.earth * 29.447498)
    on_uranus = lambda self: self._calc(self.earth * 84.016846)
    on_neptune = lambda self: self._calc(self.earth * 164.79132)
