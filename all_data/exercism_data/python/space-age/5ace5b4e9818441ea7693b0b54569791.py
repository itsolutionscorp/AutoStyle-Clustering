class SpaceAge():
    def __init__(self, seconds):
        self.seconds = seconds
        earth_age = self.seconds / 31557600
        self.ages = {'Earth':float("{0:.2f}".format(earth_age)),
                    'Mercury':float("{0:.2f}".format(earth_age*(1/0.2408467))),
                    'Venus':float("{0:.2f}".format(earth_age*(1/0.61519726))),
                    'Mars':float("{0:.2f}".format(earth_age*(1/1.8808158))),
                    'Jupiter':float("{0:.2f}".format(earth_age*(1/11.862615))),
                    'Saturn':float("{0:.2f}".format(earth_age*(1/29.447498))),
                    'Uranus':float("{0:.2f}".format(earth_age*(1/84.016846))),
                    'Neptune':float("{0:.2f}".format(earth_age*(1/164.79132)))}

    def on_earth(self):
        return self.ages['Earth']

    def on_mercury(self):
        return self.ages['Mercury']

    def on_venus(self):
        return self.ages['Venus']

    def on_mars(self):
        return self.ages['Mars']

    def on_jupiter(self):
        return self.ages['Jupiter']

    def on_saturn(self):
        return self.ages['Saturn']

    def on_uranus(self):
        return self.ages['Uranus']

    def on_neptune(self):
        return self.ages['Neptune']
