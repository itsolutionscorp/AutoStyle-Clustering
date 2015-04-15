class SpaceAge(object):
    earth_year_in_s = 31557600.
    planet_years = {'mercury': 0.2408467,
                    'venus': 0.61519726,
                    'mars': 1.8808158,
                    'jupiter': 11.862615,
                    'saturn': 29.447498,
                    'uranus': 84.016846,
                    'neptune': 164.79132}
    def __init__(self, seconds):
        self.seconds = seconds

    def on_earth(self):
        return round(self.seconds/self.earth_year_in_s,2)

    def on_mercury(self):
        return round(self.seconds/self.earth_year_in_s/self.planet_years['mercury'],2)

    def on_venus(self):
        return round(self.seconds / self.earth_year_in_s / self.planet_years['venus'], 2)

    def on_mars(self):
        return round(self.seconds / self.earth_year_in_s / self.planet_years['mars'], 2)

    def on_jupiter(self):
        return round(self.seconds / self.earth_year_in_s / self.planet_years['jupiter'], 2)

    def on_saturn(self):
        return round(self.seconds / self.earth_year_in_s / self.planet_years['saturn'], 2)

    def on_uranus(self):
        return round(self.seconds / self.earth_year_in_s / self.planet_years['uranus'], 2)

    def on_neptune(self):
        return round(self.seconds / self.earth_year_in_s / self.planet_years['neptune'], 2)
