earth_year_ratio = {
    "on_earth":   1,
    "on_mercury": 0.2408467,
    "on_venus":   0.61519726,
    "on_mars":    1.8808158,
    "on_jupiter": 11.862615,
    "on_saturn":  29.447498,
    "on_uranus":  84.016846,
    "on_neptune": 164.79132,
}

SECONDS_PER_EARTH_YEAR = 365.25 * 60 * 60 * 24

class SpaceAge():
    def __init__(self, seconds):
        self.seconds = seconds

    def __getattr__(self, planet):
        def convert():
            return round(self.seconds / 
                         (SECONDS_PER_EARTH_YEAR * earth_year_ratio[planet])
                         , 2)
        return convert
