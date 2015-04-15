class SpaceAge:

    planets = {
        'earth'   : 1.0000000,
        'mercury' : 0.2408467,
        'venus'   : 0.61519726,
        'mars'    : 1.8808158,
        'jupiter' : 11.862615,
        'saturn'  : 29.447498,
        'uranus'  : 84.016846,
        'neptune' : 164.79132
    }

    def __init__(self, age_in_seconds):
        self.seconds = age_in_seconds
        self.earth_years = float(age_in_seconds) / float(31557600)

    def __getattr__(self, name):
        if name.startswith('on_'):
            def function():
                return self.on(name[3:].lower())
            return function
        raise AttributeError("SpaceAge has no attribute '" + name + "'")

    def on(self, planet):
        try:
            orbital_period = SpaceAge.planets[planet]
            return round(self.earth_years / orbital_period, 2)
        except IndexError:
            raise ValueError(planet + ": planet unknown")
