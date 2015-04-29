def _to_day(seconds):
    return seconds / (24 * 60 * 60)

def _to_year(seconds):
    return (_to_day(seconds)) / 365.25

class SpaceAge(object):

    def __init__(self, seconds):
        self.seconds = seconds

    def _seconds_to_year(self, year_len):
        return round(_to_year(self.seconds / year_len), 2)

    def on_mercury(self):
        return self._seconds_to_year(0.2408467)

    def on_venus(self):
        return self._seconds_to_year(0.61519726)

    def on_earth(self):
        return self._seconds_to_year(1)

    def on_mars(self):
        return self._seconds_to_year(1.8808158)

    def on_jupiter(self):
        return self._seconds_to_year(11.862615)

    def on_saturn(self):
        return self._seconds_to_year(29.447498)

    def on_uranus(self):
        return self._seconds_to_year(84.016846)

    def on_neptune(self):
        return self._seconds_to_year(164.79132)
