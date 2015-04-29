EARTH_YEAR_SECONDS = 31557600
PLANET_YEAR_EARTH_YEAR = {
        'earth': 1,
        'mercury': 0.2408467,
        'venus': 0.61519726,
        'mars': 1.8808158,
        'jupiter': 11.862615,
        'saturn': 29.447498,
        'uranus': 84.016846,
        'neptune': 164.79132,
        }


class SpaceAge:
    def __init__(self, seconds):
        self.seconds = seconds
        for planet in PLANET_YEAR_EARTH_YEAR.keys():
            setattr(SpaceAge, 'on_' + planet, classmethod(SpaceAge.age_on_planet(seconds, planet)))
    @staticmethod
    def age_on_planet(seconds, planet):
        return lambda self: float('{:.2f}'.format(seconds / EARTH_YEAR_SECONDS / PLANET_YEAR_EARTH_YEAR[planet]))
