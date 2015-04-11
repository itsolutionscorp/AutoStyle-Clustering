class SpaceAge:
    EARTH_YEARS = {
        'on_mercury': 0.2408467,
        'on_venus': 0.61519726,
        'on_earth': 1,
        'on_mars': 1.8808158,
        'on_jupiter': 11.862615,
        'on_saturn': 29.447498,
        'on_uranus': 84.016846,
        'on_neptune': 164.79132
    }
    
    SECONDS_PER_EARTH_YEAR = 31557600

    def __init__(self, seconds):
        self.seconds = seconds

    def __getattribute__(self, name):
        if name in SpaceAge.EARTH_YEARS:
            return lambda: round(self.seconds / SpaceAge.SECONDS_PER_EARTH_YEAR / SpaceAge.EARTH_YEARS[name], 2)
        else:
            return super().__getattribute__(name)
