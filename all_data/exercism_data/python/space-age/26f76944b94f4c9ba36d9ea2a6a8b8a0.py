EARTH_SECONDS = 31557600.0
PLANETS = {"on_mercury":0.2408467, "on_venus":0.61519726, "on_earth":1,
           "on_mars":1.8808158, "on_jupiter":11.862615, "on_saturn":29.447498,
           "on_uranus":84.016846, "on_neptune":164.79132,}


class SpaceAge():
    def __init__(self, seconds, precision=2):
        self.seconds = seconds
        self.pre = precision

    def __getattr__(self, attr_name):
        def years():
            return round(self.terra_year() / PLANETS[attr_name], self.pre)
        return years

    def terra_year(self):
        return self.seconds / EARTH_SECONDS
