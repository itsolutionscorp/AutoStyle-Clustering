earth_year = 31557600

years = {
    'mercury' : earth_year * 0.2408467,
    'venus' : earth_year * 0.61519726,
    'mars' : earth_year * 1.8808158,
    'jupiter' : earth_year * 11.862615,
    'saturn' : earth_year * 29.447498,
    'uranus' : earth_year * 84.016846,
    'neptune' : earth_year * 164.79132
    }

class SpaceAge:
    def __init__(self, seconds):
        self.seconds = seconds

    def on_earth(self):
        return round(self.seconds/earth_year, 2)

    def on_mercury(self):
        return round(self.seconds/years['mercury'], 2)

    def on_venus(self):
        return round(self.seconds/years['venus'], 2)

    def on_mars(self):
        return round(self.seconds/years['mars'], 2)

    def on_jupiter(self):
        return round(self.seconds/years['jupiter'], 2)

    def on_saturn(self):
        return round(self.seconds/years['saturn'], 2)

    def on_uranus(self):
        return round(self.seconds/years['uranus'], 2)

    def on_neptune(self):
        return round(self.seconds/years['neptune'], 2)
