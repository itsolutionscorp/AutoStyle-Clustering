class SpaceAge:

    years_dict = {
        'on_earth': 1,
        'on_mercury': 0.2408467,
        'on_venus': 0.61519726,
        'on_mars': 1.8808158,
        'on_jupiter': 11.862615,
        'on_saturn': 29.447498,
        'on_uranus': 84.016846,
        'on_neptune': 164.79132,
        }

    def __init__(self, age_in_seconds):
        self.seconds = age_in_seconds

    def __getattr__(self, attr_name):
        def years():
            return round(self.earth_year()/self.years_dict[attr_name], 2)
        return years

    def earth_year(self):
        return self.seconds/31557600
