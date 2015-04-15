'''exer space_age'''

class SpaceAge:
    '''classify a number of seconds interms of planetary orbits.
    Pluto, discounting the hyperbole of the IAU is a planet.  See
    http://www.plutoisaplanet.com/ '''

    earth_year_in_seconds = 31557600
    planets_orbits = {
        'Mercury': 0.2408467 * earth_year_in_seconds,
        'Venus':  0.61519726 * earth_year_in_seconds,
        'Earth': 1.0 * earth_year_in_seconds,
        'Mars':  1.8808158 * earth_year_in_seconds,
        'Jupiter':  11.862615 * earth_year_in_seconds,
        'Saturn':  29.447498 * earth_year_in_seconds,
        'Uranus':  84.016846 * earth_year_in_seconds,
        'Neptune':  164.79132 * earth_year_in_seconds,
        'Pluto':  247.68 * earth_year_in_seconds,
    }

    def __init__(self, secs):
        '''set the number of seconds for the conversions'''
        self.seconds = secs

    def on_(self, planet):
        '''return the number of years on that planet'''
        return round(self.seconds / SpaceAge.planets_orbits[planet], 2)

    def on_earth(self):
        '''return number of years on earth'''
        return self.on_('Earth')

    def on_mercury(self):
        '''return number of years on mercury'''
        return self.on_('Mercury')

    def on_venus(self):
        '''return number of years on venus'''
        return self.on_('Venus')

    def on_mars(self):
        '''return number of years on mars'''
        return self.on_('Mars')

    def on_jupiter(self):
        '''return number of years on jupiter'''
        return self.on_('Jupiter')

    def on_saturn(self):
        '''return number of years on saturn'''
        return self.on_('Saturn')

    def on_uranus(self):
        '''return number of years on uranus'''
        return self.on_('Uranus')

    def on_neptune(self):
        '''return number of years on neptune'''
        return self.on_('Neptune')

    def on_pluto(self):
        '''return number of years on pluto'''
        return self.on_('Pluto')
