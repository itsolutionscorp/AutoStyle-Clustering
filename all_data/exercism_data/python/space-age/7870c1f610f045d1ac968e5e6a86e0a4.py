earth_year_len = 31557600
years = {"earth": 1,
         "mercury": 0.2408467,
         "venus": 0.61519726,
         "mars": 1.8808158,
         "jupiter": 11.862615,
         "saturn": 29.447498,
         "uranus": 84.016846,
         "neptune": 164.79132}


class SpaceAge:
    def __init__(self, seconds):
        self.seconds = seconds

    def __getattr__(self, name):
        if name.startswith("on_"):
            try:
                conv = years[name[3:]] * earth_year_len
            except KeyError:
                raise AttributeError
        return lambda: round(self.seconds / conv, 2)
