class SpaceAge(object):

    def __init__(self, seconds):
        self.seconds = seconds
        self.year_in_seconds = {
            "mercury": 7600543.81992,
            "venus": 19414149.052176,
            "earth": 31557600,
            "mars": 59354032.690079994,
            "jupiter": 374355659.124,
            "saturn": 929292362.8848,
            "uranus": 2651370019.3296,
            "neptune": 5200418560.032001
        }
        for planet in self.year_in_seconds:
            def on_planet_x(planet=planet):
                return self.on_planet(planet)
            setattr(self, 'on_' + planet, on_planet_x)

    def on_planet(self, planet):
        return round(self.seconds / self.year_in_seconds[planet], 2)
