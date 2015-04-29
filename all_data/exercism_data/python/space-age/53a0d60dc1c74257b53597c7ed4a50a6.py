class SpaceAge:
    #these are in seconds and sidereal
    #see https://en.wikipedia.org/wiki/Orbital_period
    earth_solar_year = 31557600.0
    mercury_solar_year = 7600521.6
    venus_solar_year = 19414166.4
    mars_solar_year = 59355072.0
    jupiter_solar_year = 374328256.0
    saturn_solar_year = 929603925.0
    uranus_solar_year = 2651223580.0
    neptune_solar_year = 5200360500.0

    def __init__(self, seconds, precision=2):
        self.seconds = seconds
        self.precision = precision

    def on_earth(self):
        return round(self.seconds / SpaceAge.earth_solar_year, self.precision)

    def on_mercury(self):
        return round(self.seconds / SpaceAge.mercury_solar_year, self.precision)

    def on_venus(self):
        return round(self.seconds / SpaceAge.venus_solar_year, self.precision)

    def on_mars(self):
        return round(self.seconds / SpaceAge.mars_solar_year, self.precision)

    def on_jupiter(self):
        return round(self.seconds / SpaceAge.jupiter_solar_year, self.precision)

    def on_saturn(self):
        return round(self.seconds / SpaceAge.saturn_solar_year, self.precision)

    def on_uranus(self):
        return round(self.seconds / SpaceAge.uranus_solar_year, self.precision)

    def on_neptune(self):
        return round(self.seconds / SpaceAge.neptune_solar_year, self.precision)
