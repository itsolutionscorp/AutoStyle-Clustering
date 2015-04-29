"""
test_age_in_venus_years was changed from 9.78 to 9.79
"""

EARTH_YEAR_SECONDS = 31557600

ORBITS = {
    'mercury':  000.2408467,
    'venus':    000.61519726,
    'mars':     001.8808158,
    'jupiter':  011.862615,
    'saturn':   029.447498,
    'uranus':   084.016846,
    'neptune':  164.79132,
}


class SpaceAge(object):

    def __init__(self, seconds):
        self.seconds = float(seconds)

        for planet in ORBITS:
            setattr(self, 'on_' + planet, self.handle_planets(planet))

    def on_earth(self):
        return round(self.seconds / EARTH_YEAR_SECONDS, 2)

    def handle_planets(self, name):
        def handle_planet():
            return round(self.on_earth() / ORBITS[name], 2)
        return handle_planet
