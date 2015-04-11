class SpaceAge(object):
    _year_in_seconds = 24 * 3600 * 365.25
    years = {
        'earth': 1 * _year_in_seconds,
        'mercury': 0.2408467 * _year_in_seconds,
        'venus':  0.61519726 * _year_in_seconds,
        'mars': 1.8808158 * _year_in_seconds,
        'jupiter': 11.862615 * _year_in_seconds,
        'saturn': 29.447498 * _year_in_seconds,
        'uranus': 84.016846 * _year_in_seconds,
        'neptune': 164.79132 * _year_in_seconds
    }

    def __init__(self, seconds):
        self.seconds = seconds

    def on_earth(self):
        return round(self.seconds / self.years['earth'], 2)

    def on_mercury(self):
        return round(self.seconds / self.years['mercury'], 2)

    def on_venus(self):
        return round(self.seconds / self.years['venus'], 2)

    def on_mars(self):
        return round(self.seconds / self.years['mars'], 2)

    def on_jupiter(self):
        return round(self.seconds / self.years['jupiter'], 2)

    def on_saturn(self):
        return round(self.seconds / self.years['saturn'], 2)

    def on_uranus(self):
        return round(self.seconds / self.years['uranus'], 2)

    def on_neptune(self):
        return round(self.seconds / self.years['neptune'], 2)
