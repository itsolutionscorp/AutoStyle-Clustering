class SpaceAge:

    year_on = {
                'earth': 1,
                'mercury': 0.2408467,
                'venus': 0.61519726,
                'mars': 1.8808158,
                'jupiter': 11.862615,
                'saturn': 29.447498,
                'uranus': 84.016846,
                'neptune': 164.79132,
              }


    def __init__(self, seconds):
        self.seconds = seconds

    def __getattr__(self, name):
        if name.startswith("on_"):
            def age():
                return round(self.seconds / (self.year_on[name[3:]] * 31557600), 2)

            return age
