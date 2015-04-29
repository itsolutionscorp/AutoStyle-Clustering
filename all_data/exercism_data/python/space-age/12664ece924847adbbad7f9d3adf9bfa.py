class SpaceAge(object):
    earth_year_in_s = 31557600.
    planet_years = {'earth': 1,
                    'mercury': 0.2408467,
                    'venus': 0.61519726,
                    'mars': 1.8808158,
                    'jupiter': 11.862615,
                    'saturn': 29.447498,
                    'uranus': 84.016846,
                    'neptune': 164.79132}
    def __init__(self, seconds):
        self.seconds = seconds

    def __getattr__(self, item):
        return (lambda: round(self.seconds / self.earth_year_in_s / self.planet_years[item[3:]], 2))
