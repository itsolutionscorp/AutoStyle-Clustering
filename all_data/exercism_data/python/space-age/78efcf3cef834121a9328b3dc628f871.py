class SpaceAge():

    def __init__(self, seconds):
        self.seconds = seconds
        self.earth = seconds / 31557600
        self.periods = {'earth': 1,
             'jupiter': 11.862615,
             'mars': 1.8808158,
             'mercury': 0.2408467,
             'neptune': 164.79132,
             'saturn': 29.447498,
             'uranus': 84.016846,
             'venus': 0.61519726}

    def __getattr__(self,onplanet):
        if onplanet.startswith('on_'):
            # detect if name corresponds to naming scheme
            planet = onplanet.split("_")[-1]
            if planet in self.periods:
                # generate the function when it is called
                return lambda : round(self.earth/self.periods[planet], 2)
            else :
                return None
        else:
            return None
