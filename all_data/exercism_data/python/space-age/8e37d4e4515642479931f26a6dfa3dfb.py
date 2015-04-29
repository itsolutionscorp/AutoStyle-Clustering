class SpaceAge():
    EARTH_SECONDS = 31557600.0
    EARTH_DAYS = 365.25

    def __init__(self, seconds):
        self.seconds = seconds

    def calc_age(self, orbital_period):
        age = self.seconds / self.EARTH_SECONDS / orbital_period
        return round(age, 2)

    def on_earth(self):
        orbital_period = 1.0
        return self.calc_age(orbital_period)

    def on_mercury(self):
        orbital_period = 0.2408467
        return self.calc_age(orbital_period)

    def on_venus(self):
        orbital_period = 0.61519726
        return self.calc_age(orbital_period)

    def on_mars(self):
        orbital_period = 1.8808158
        return self.calc_age(orbital_period)

    def on_saturn(self):
        orbital_period = 29.447498
        return self.calc_age(orbital_period)

    def on_jupiter(self):
        orbital_period = 11.862615
        return self.calc_age(orbital_period)

    def on_uranus(self):
        orbital_period = 84.016846
        return self.calc_age(orbital_period)

    def on_neptune(self):
        orbital_period = 164.79132
        return self.calc_age(orbital_period)
