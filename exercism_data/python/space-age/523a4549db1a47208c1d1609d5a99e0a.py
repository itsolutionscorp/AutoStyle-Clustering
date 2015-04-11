class SpaceAge:
    #these are in seconds and sidereal
    #see https://en.wikipedia.org/wiki/Orbital_period
    solar_year = {'earth'   : 31557600.0,   'mercury' : 7600521.6,
                  'venus'   : 19414166.4,   'mars'    : 59355072.0,
                  'jupiter' : 374328256.0,  'saturn'  : 929603925.0,
                  'uranus'  : 2651223580.0, 'neptune' : 5200360500.0}

    def __init__(self, seconds, precision=2):
        self.seconds = seconds
        self.precision = precision

    def get_solar_year(self, body):
        return round(self.seconds / SpaceAge.solar_year[body], self.precision)

    def on_earth(self):
        return self.get_solar_year('earth')

    def on_mercury(self):
        return self.get_solar_year('mercury')

    def on_venus(self):
        return self.get_solar_year('venus')

    def on_mars(self):
        return self.get_solar_year('mars')

    def on_jupiter(self):
        return self.get_solar_year('jupiter')

    def on_saturn(self):
        return self.get_solar_year('saturn')

    def on_uranus(self):
        return self.get_solar_year('uranus')

    def on_neptune(self):
        return self.get_solar_year('neptune')
