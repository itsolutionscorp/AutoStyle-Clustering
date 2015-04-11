EarthSolarYear = 31557600
SolarYearsPerEarthSolarYear = {
    'earth': 1.0,
    'mercury': 0.2408467,
    'venus': 0.61519726,
    'mars': 1.8808158,
    'jupiter': 11.862615,
    'saturn': 29.447498,
    'uranus': 84.016846,
    'neptune': 164.79132
}

class SpaceAge(object):
    def __init__(self, age):
        self.seconds = float(age)

    def __getattr__(self, item):
        planet = item.replace("on_", "")

        if planet in SolarYearsPerEarthSolarYear:
            return Callable(round(self.seconds / EarthSolarYear / SolarYearsPerEarthSolarYear[planet], 2))
        else:
            return object.__getattribute__(self, item)

class Callable(object):
    def __init__(self, value):
        self.value = value

    def __call__(self, *args, **kwargs):
        return self.value
