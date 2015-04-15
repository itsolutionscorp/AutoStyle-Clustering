from datetime import timedelta

EarthSolarYear = 31557600
SolarYearsPerEarthSolarYear = {
    'earth': 1,
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
        self.age = timedelta(seconds = age)

    @property
    def seconds(self):
        return self.age.total_seconds()

    def on_earth(self, divisor = 1):
        return round(self.age.total_seconds() / EarthSolarYear / divisor, 2)

    def on_mercury(self):
        return self.on_earth(SolarYearsPerEarthSolarYear['mercury'])

    def on_venus(self):
        return self.on_earth(SolarYearsPerEarthSolarYear['venus'])

    def on_mars(self):
        return self.on_earth(SolarYearsPerEarthSolarYear['mars'])

    def on_jupiter(self):
        return self.on_earth(SolarYearsPerEarthSolarYear['jupiter'])

    def on_saturn(self):
        return self.on_earth(SolarYearsPerEarthSolarYear['saturn'])

    def on_uranus(self):
        return self.on_earth(SolarYearsPerEarthSolarYear['uranus'])

    def on_neptune(self):
        return self.on_earth(SolarYearsPerEarthSolarYear['neptune'])
