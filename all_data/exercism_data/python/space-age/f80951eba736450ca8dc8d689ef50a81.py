class SpaceAge:
    def __init__(self, seconds):
        self.seconds = seconds
        self._multipliers = {'earth': 1,
                'mercury': 0.2408467,
                'venus': 0.61519726,
                'mars': 1.8808158,
                'jupiter': 11.862615,
                'saturn': 29.447498,
                'uranus': 84.016846,
                'neptune': 164.79132}
        self._const = 365.25 * 24 * 60 * 60
        for p in self._multipliers:
            fun = self._fun_gen(p)
            setattr(self, 'on_{}'.format(p), fun)
    def _fun_gen(self, planet):
        def f():
            age = self._multipliers[planet]*self.seconds/self._const
            #return(round(age, 2))
            return(age)
        return(f)
