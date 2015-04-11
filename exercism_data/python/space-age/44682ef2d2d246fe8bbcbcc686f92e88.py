def _to_day(seconds):
    return seconds / (24 * 60 * 60)

def _to_year(seconds):
    return (_to_day(seconds)) / 365.25

class SpaceAge(object):

    def __init__(self, seconds):
        self.seconds = seconds

    def on_mercury(self):
        return round(_to_year(self.seconds / 0.2408467), 2)

    def on_venus(self):
        return round(_to_year(self.seconds / 0.61519726), 2)

    def on_earth(self):
        return round(_to_year(self.seconds), 2)

    def on_mars(self):
        return round(_to_year(self.seconds / 1.8808158), 2)

    def on_jupiter(self):
        return round(_to_year(self.seconds / 11.862615), 2)

    def on_saturn(self):
        return round(_to_year(self.seconds / 29.447498), 2)

    def on_uranus(self):
        return round(_to_year(self.seconds / 84.016846), 2)

    def on_neptune(self):
        return round(_to_year(self.seconds / 164.79132), 2)
