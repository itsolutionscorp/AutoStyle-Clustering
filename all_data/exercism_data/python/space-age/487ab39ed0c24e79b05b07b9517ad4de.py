class SpaceAge:

    def __init__(self, seconds=1e6):
        self.seconds = seconds
        self.earth = 31557600
        self.planets = {
            'earth': 1.0,
            'mercury': 0.2408467,
            'venus': 0.61519726,
            'mars': 1.8808158,
            'jupiter': 11.862615,
            'saturn': 29.447498,
            'uranus': 84.016846,
            'neptune': 164.79132,
        }

        for i, v in self.planets.items():
            setattr(self, 'on_{}'.format(i), self.calc(v))

    def calc(self, planet):
        return lambda: round(self.seconds / (planet * self.earth), 2)
