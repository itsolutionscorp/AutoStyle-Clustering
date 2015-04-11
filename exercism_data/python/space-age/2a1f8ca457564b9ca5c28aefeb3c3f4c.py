class SpaceAge:
    orbital_periods = {
        "Earth":   31557600.0000,
        "Mercury": 7600543.81992,
        "Venus":   19414149.0522, 
        "Mars":    59354032.6901,
        "Jupiter": 374355659.124, 
        "Saturn":  929292362.885,
        "Uranus":  2651370019.33,
        "Neptune": 5200418560.03,
    }

    def __init__(self, seconds):
        self.seconds = seconds

        for planet, orbital_period in SpaceAge.orbital_periods.items():
            self._remember("on_" + planet.lower(),
                          self._make_planet_age(orbital_period))

    def _make_planet_age(self, orbital_period):
        def age_on_planet():
            return round(self.seconds / orbital_period, 2)
        return age_on_planet
 
    def _remember(self, name, function):
        self.__dict__[name] = function
       
    pass
