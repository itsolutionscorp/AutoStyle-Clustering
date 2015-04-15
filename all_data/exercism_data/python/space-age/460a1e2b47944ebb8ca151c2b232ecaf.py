class SpaceAge():

    _planet_year_multipliers = {
        'mercury': 0.2408467,
        'venus': 0.61519726,
        'earth': 1,
        'mars': 1.8808158,
        'jupiter': 11.862615,
        'saturn': 29.447498,
        'uranus': 84.016846,
        'neptune': 164.79132,
    }

    def __init__(self, seconds):
        self.seconds = seconds
        self._earth_years = seconds / 31557600

    def __getattr__(self, name):
        '''
        On attribute lookup, if the attribute's name has the form
        'on_{planet}', this looks up 'planet' in the multiplier dictionary and
        uses the result as the multiplier for earth years, which we've already
        calculated in __init__.
        '''
        valid_name = name.startswith('on_')
        valid_name &= name[3:] in self._planet_year_multipliers.keys()
        if not valid_name:
            raise AttributeError

        mult = self._planet_year_multipliers[name[3:]]
        return lambda: round(self._earth_years / mult, 2)
