
EARTH_ORBIT_SECONDS = 31557600

MERCURY_FROM_EARTH = 0.2408467
VENUS_FROM_EARTH   = 0.61519726
MARS_FROM_EARTH    = 1.8808158
JUPITER_FROM_EARTH = 11.862615
SATURN_FROM_EARTH  = 29.447498
URANUS_FROM_EARTH  = 84.016846
NEPTUNE_FROM_EARTH = 164.79132
    
class SpaceAge:    
    def __init__(self, age_in_seconds):
        self.seconds = age_in_seconds
        self._on_earth = self.seconds / EARTH_ORBIT_SECONDS

    def on_earth(self):
        return round(self._on_earth, 2)

    def on_mercury(self):
        return round(self._on_earth / MERCURY_FROM_EARTH, 2)

    def on_venus(self):
        return round(self._on_earth / VENUS_FROM_EARTH, 2)

    def on_mars(self):
        return round(self._on_earth / MARS_FROM_EARTH, 2)

    def on_jupiter(self):
        return round(self._on_earth / JUPITER_FROM_EARTH, 2)

    def on_saturn(self):
        return round(self._on_earth / SATURN_FROM_EARTH, 2)

    def on_uranus(self):
        return round(self._on_earth / URANUS_FROM_EARTH, 2)

    def on_neptune(self):
        return round(self._on_earth / NEPTUNE_FROM_EARTH, 2)
