from functools import partial


class SpaceAge:
    multiplier = {"earth": 1,
                  "mercury": 0.2408467,
                  "venus": 0.61519726,
                  "mars": 1.8808158,
                  "jupiter": 11.862615,
                  "saturn": 29.447498,
                  "uranus": 84.016846,
                  "neptune": 164.79132}

    def __init__(self, seconds):
        self.seconds = seconds

        # Probably inadvisable, but I'd rather not create eight
        # near-identical functions by hand as the tests require
        for planet in self.multiplier.keys():
            f = partial(self.on_planet, planet)
            setattr(self, ("on_%s" % planet), f)

    def on_planet(self, planet):
        return round(self.seconds / (31557600 * self.multiplier[planet]), 2)
