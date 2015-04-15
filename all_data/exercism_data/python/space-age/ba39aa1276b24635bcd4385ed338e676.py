class SpaceAge():
    earth = 31557600.0

    scale = {
        'on_mercury': 0.2408467,
        'on_venus': 0.61519726,
        'on_earth': 1,
        'on_mars': 1.8808158,
        'on_jupiter': 11.862615,
        'on_saturn': 29.447498,
        'on_neptune': 164.79132,
        'on_uranus': 84.016846,
    }

    def __init__(self, seconds):
        self.seconds = seconds

    def __getattr__(self, item):
        return lambda: round(self.seconds / (self.earth * self.scale[item]), 2)
