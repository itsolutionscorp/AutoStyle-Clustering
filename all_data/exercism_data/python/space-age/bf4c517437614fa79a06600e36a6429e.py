class SpaceAge:
    EARTH = 31557600

    PLANET_YEARS = {
        'on_earth': EARTH,
        'on_mercury': 0.2408467 * EARTH,
        'on_venus': 0.61519726 * EARTH,
        'on_mars': 1.8808158 * EARTH,
        'on_jupiter': 11.862615 * EARTH,
        'on_saturn': 29.447498 * EARTH,
        'on_uranus': 84.016846 * EARTH,
        'on_neptune': 164.79132 * EARTH
    }

    def __init__(self, seconds):
        self.seconds = seconds

    def __getattr__(self, name):
        def handler_function(*args, **kwargs):
            return round(self.seconds / self.PLANET_YEARS[name], 2)

        return handler_function
