class SpaceAge:
    conversion = {
        'earth': 1,
        'mercury': 0.2408467,
        'venus': 0.61519726,
        'mars': 1.8808158,
        'jupiter': 11.862615,
        'saturn': 29.447498,
        'uranus': 84.016846,
        'neptune': 164.79132
    }

    def __init__(self, seconds):
        self.seconds = seconds

        for planet in self.conversion:
            setattr(SpaceAge, 'on_' + planet, generic_convert(planet))


def generic_convert(planet):
    def specific_convert(self):
        return round(self.seconds / 31557600 / self.conversion.get(planet), 2)
    return specific_convert
