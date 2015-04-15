class SpaceAge:

    years = {
        'Earth': 31557600.0,
        'Mercury': 0.2408467,
        'Venus': 0.61519726,
        'Mars': 1.8808158,
        'Jupiter': 11.862615,
        'Saturn': 29.447498,
        'Uranus': 84.016846,
        'Neptune': 164.79132,
    }
    
    def __init__(self, seconds):
        self.seconds = seconds
        for planet in self.years.keys():
            def on_planet_x(planet=planet):
                return self.on_planet(planet)
            setattr(self, 'on_%s' % planet.lower(), on_planet_x)

    def on_planet(self, planet):
        on_earth = self.seconds / 31557600.00
        on_planet = on_earth / self.years[planet]
        return round(on_earth if planet == 'Earth' else on_planet, 2)
        
