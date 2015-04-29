class SpaceAge:

    def __init__(self, age):
        self.seconds = age
        self.earthyears = self.seconds / 31557600.0
        self.planets = {"earth": 1, "mercury": 0.2408467, "venus": 0.61519726,
                        "mars": 1.8808158, "jupiter": 11.862615,
                        "saturn": 29.447498, "uranus": 84.016846,
                        "neptune": 164.79132}

    def __getattr__(self, name):
        def method(*args):
            if name[:3] == "on_":
                return round(self.earthyears / self.planets[name[3:]], 2)
        return method
