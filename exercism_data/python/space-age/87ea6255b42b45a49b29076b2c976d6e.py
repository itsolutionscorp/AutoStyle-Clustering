class SpaceAge(object):

    def __init__(self, seconds):
        self.seconds = seconds
        self._earthage = self.seconds / 31557600.0

    def on_earth(self):
        return self._rounded_calc(1)

    def on_mercury(self):
        return self._rounded_calc(0.2408467)

    def on_venus(self):
        return self._rounded_calc(0.61519726)

    def on_mars(self):
        return self._rounded_calc(1.8808158)

    def on_jupiter(self):
        return self._rounded_calc(11.862615)

    def on_saturn(self):
        return self._rounded_calc(29.447498)

    def on_uranus(self):
        return self._rounded_calc(84.016846)

    def on_neptune(self):
        return self._rounded_calc(164.79132)

    def _rounded_calc(self, period):
        return round(self._earthage / period, 2)
