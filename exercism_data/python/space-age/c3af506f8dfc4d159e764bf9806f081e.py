""" module school for exercism.io programming training """


class SpaceAge():
    def __init__(self, seconds):
        self.seconds = seconds
    
    
    def on_earth(self):
        return round(664.71 / 20976670057.0 * self.seconds, 2)


    def on_mercury(self):
        return round(280.88 / 2134835688 * self.seconds, 2)


    def on_venus(self):
        return round(9.78 / 189839836 * self.seconds, 2)


    def on_mars(self):
        return round(39.25 / 2329871239 * self.seconds, 2)


    def on_jupiter(self):
        return round(2.41 / 901876382 * self.seconds, 2)


    def on_saturn(self):
        return round(3.23 / 3e9 * self.seconds, 2)


    def on_uranus(self):
        return round(1.21 / 3210123456 * self.seconds, 2)


    def on_neptune(self):
        return round(1.58 / 8210123456 * self.seconds, 2)
